package com.tmacbo.eqdushu.ui.settting;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.tmacbo.eqdushu.BuildConfig;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.CommonConfig;
import com.tmacbo.eqdushu.common.Utils;
import com.tmacbo.eqdushu.common.base.BaseActivity;

/**
 * @Author tmacbo
 * @Since on 2017/11/28  01:18
 * @mail tang_bo@hotmail.com
 * @Description 关于我们
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.imageViewIcon)
    ImageView imageViewIcon;

    @BindView(R.id.textSysInfo)
    TextView textSysInfo;

    @BindView(R.id.textViewVer)
    TextView textViewVer;

    private int num = 0;

    @Override
    protected int layoutRes() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        setTitle("关于我们");

        textViewVer.setText(CommonConfig.getAppVersion(this));
        textSysInfo.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        imageViewIcon.setOnClickListener(this);
    }

    public void show() {
        findViewById(R.id.textViewVerInfo).setVisibility(View.GONE);
        //        textViewMobile.setVisibility(View.GONE);
        textSysInfo.setVisibility(View.VISIBLE);
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("渠道号: ");
        stringBuffer.append(CommonConfig.getUmengChannel(this));
        stringBuffer.append("\n");

        stringBuffer.append("IMEI: ");
        stringBuffer.append(CommonConfig.getIMEI(this));
        stringBuffer.append("\n");
        stringBuffer.append("MAC地址: ");
        stringBuffer.append(CommonConfig.getMAC_ADDRESS());
        stringBuffer.append("\n");

        stringBuffer.append("服务器: ");
        stringBuffer.append(CommonConfig.getBASE_URL());
        stringBuffer.append("\n");

        stringBuffer.append("混淆标志: ");
        stringBuffer.append(CommonConfig.isMutil() ? "已混淆" : "未混淆");
        stringBuffer.append("\n");

        stringBuffer.append("极光ID: ");
        stringBuffer.append(CommonConfig.PUSH_ID);
        stringBuffer.append("\n");

        stringBuffer.append("日志开关: ");
        stringBuffer.append(BuildConfig.DEBUG ? "开" : "关");
        stringBuffer.append("\n");

        stringBuffer.append("屏幕信息: ");
        stringBuffer.append(getHeightPixels() + "x" + getWidthPixels() + "(" + getDensity() + ")");
        stringBuffer.append("\n\n");

        String sign = Utils.getSignInfo(this);

        int index = 0;
        StringBuffer errorInfo = new StringBuffer("");
        boolean isOnline = true;

        if (!CommonConfig.getJPushCode(this).equals(CommonConfig.getONLINE_JPUSH_KEY())) {
            isOnline = false;
            errorInfo.append(++index + ". 极光推送KEY错误\n");
        }

        if (!CommonConfig.getUMengKey(this).equals(CommonConfig.getONLINE_UMENG_KEY())) {
            isOnline = false;
            errorInfo.append(++index + ". 友盟KEY错误\n");
        }
        if (!CommonConfig.getAppPackageName(this).equals(CommonConfig.getONLINE_PACKAGE_NAME())) {
            isOnline = false;
            errorInfo.append(++index + ". 包名错误\n");
        }

        if (!CommonConfig.isMutil()) {
            isOnline = false;
            errorInfo.append(++index + ". 代码未混淆\n");
        }

        if (BuildConfig.DEBUG) {
            isOnline = false;
            errorInfo.append(++index + ". 日志开关被打开\n");
        }
        if (!CommonConfig.getONLINE_SIGN_MD5().equals(sign)) {
            isOnline = false;
            errorInfo.append(++index + ". 与生产证书不符\n");
        }

        if (isOnline) {
            stringBuffer.append("这是一个生产版本");
            stringBuffer.append("\n");
        } else {
            stringBuffer.append("警告:测试版本\n");
            stringBuffer.append(errorInfo.toString());
        }

        textSysInfo.setText(stringBuffer);
    }

    @Override
    public int getWidthPixels() {
        return super.getWidthPixels();
    }

    @Override
    protected void onPause() {
        super.onPause();
        num = 0;
        textSysInfo.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == imageViewIcon) {
            num++;
            if (num >= 10) {
                show();
            }
        }
    }
}
