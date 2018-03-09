package com.tmacbo.eqdushu.ui.book;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.util.FRToast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.presenter.book.BookInLibraryPresenter;
import com.tmacbo.eqdushu.utils.qrcode.camera.CameraManager;
import com.tmacbo.eqdushu.utils.qrcode.decoding.CaptureActivityHandler;
import com.tmacbo.eqdushu.utils.qrcode.decoding.InactivityTimer;
import com.tmacbo.eqdushu.utils.qrcode.view.ViewfinderView;
import java.io.IOException;
import java.util.Vector;

/**
 * @Author zhangyuanlong
 * @Since on 16/3/30  15:18
 * @Description 二维码扫描页面
 */
public class QRScanActivity extends BaseActivity<BookInLibraryPresenter>
    implements Callback, BookDetailView<BaseResponse> {

    private CaptureActivityHandler handler;

    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    @BindView(R.id.viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.preview_view)
    SurfaceView surfaceView;

    private String from;
    private String isbn;

    @Override
    protected BookInLibraryPresenter createPresenter() {
        return new BookInLibraryPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_qr;
    }

    @Override
    protected void initView() {

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void initData() {
        from = getIntent().getStringExtra("from");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CameraManager.init(this);
        surfaceView.setVisibility(View.VISIBLE);

        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager)getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
        }
        handler = null;
        surfaceView.setVisibility(View.INVISIBLE);
        CameraManager.get().closeDriver();
    }

    /**
     * 处理扫描结果
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        if (result == null || result.getText().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.tips_decode_null),
                           Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", result.getText());
            if (result.getText() == null) {
                return;
            }
            isbn = result.getText();
            if ("addBook".equals(from)) {
                gotoBookDetail();
            } else if ("backBook".equals(from)) {
                backBook();
            } else {
                borrowBook();
            }
        }
    }

    private void gotoBookDetail() {
        mPresenter.getBookDetailInfo(this);
    }

    private void borrowBook() {
        mPresenter.bookBorrow(this);
    }

    private void backBook() {
        mPresenter.backBorrow(this);
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                                          file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 1000L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
    }

    @Override
    public void onRequestSuccessData(BaseResponse data) {
        if ("addBook".equals(from)) {
            FRToast.showToastSafe("添加成功");
        } else if ("backBook".equals(from)) {
            FRToast.showToastSafe("还书成功");
        } else {
            FRToast.showToastSafe("借书成功");
        }
        this.setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    public String getIsBn() {
        return isbn;
    }
}