package com.tmacbo.eqdushu.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.activity.AdminMainActivity;
import com.tmacbo.eqdushu.common.CommonConfig;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.ui.login.LoginActivity;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.ReaderMainActivity;
import com.tmacbo.eqdushu.ui.register.CreateCompanyActivity;
import com.tmacbo.eqdushu.utils.CommonConstants;
import com.tmacbo.eqdushu.utils.permissionlib.PerUtils;
import com.tmacbo.eqdushu.utils.permissionlib.PermissionEnum;

/**
 * Created by tmacbo on 2017/6/14
 */

public class SplashActivity extends BaseActivity {
    private static int PERMISSIONS_REQUEST_CODE = CommonConstants.FIRST_VAL++;
    /**
     * 是否拥有必要权限
     */
    private boolean hasRermission;
    private boolean isFromSetting = false;

    @Override
    protected int layoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        applyAllPermissions();
    }

    private void gotoNextActivity() {
        if (UserDao.getInstance().isHaveUser()) {
            if ("reader".equals(UserDao.getInstance().getUserType())) {
                startActivity(new Intent(this, ReaderMainActivity.class));
            } else if ("admin".equals(UserDao.getInstance().getUserType())) {
                startActivity(new Intent(this, AdminMainActivity.class));
            } else if ("guest".equals(UserDao.getInstance().getUserType())) {
                startActivity(new Intent(this, CreateCompanyActivity.class));
            }
            CommonConfig.USER_TYPE = UserDao.getInstance().getUserType();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFromSetting) {
            gotoNextActivity();
        }
    }

    /**
     * 监测权限
     * 这里只检查必需的权限
     */
    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            try {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else if (ContextCompat.checkSelfPermission(this,
                                                             Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else if (ContextCompat.checkSelfPermission(this,
                                                             Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else if (ContextCompat.checkSelfPermission(this,
                                                             Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else if (ContextCompat.checkSelfPermission(this,
                                                             Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else {
                    return true;
                }
            } catch (RuntimeException e) {
                //某些手机关闭了授权申请
                return false;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
        int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (checkPermissions()) {
                //权限通过了
                hasRermission = true;
                //拿到权限后需要重新初始化
                gotoNextActivity();
            } else {
                //有权限没有通过.
                showPermissionsDialog();
            }
        }
    }

    /**
     * 授权弹出框
     */
    private void showPermissionsDialog() {
        if (isDestroyedCompatible()) {
            return;
        }
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        new MaterialDialog.Builder(this).title(R.string.eye_err_permission_title)
                                        .content(R.string.eye_err_permission_msg)
                                        .autoDismiss(false)
                                        .cancelable(false)
                                        .positiveText(R.string.eye_err_permission_positive)
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {
                                                dialog.dismiss();
                                                //                        applyAllPermissions();
                                                isFromSetting = true;
                                                PerUtils.PermissionDenied(SplashActivity.this,
                                                                          PermissionEnum.NEED_SOME);
                                            }
                                        })
                                        .negativeText(R.string.eye_exit)
                                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {
                                                dialog.dismiss();
                                                //                        EventBus.getDefault().post(new EventExit());
                                                gotoNextActivity();
                                            }
                                        })
                                        .show();
    }

    /**
     * 申请权限
     * * 必需的权限
     * 1、读取手机状态 android.permission.READ_PHONE_STATE
     * 2、缓存处理 android.permission.WRITE_EXTERNAL_STORAGE
     * 3、摄像头权限 android.permission.CAMERA
     * <p>
     * 非必需的权限
     * <p>
     * 1、定位 android.permission.ACCESS_COARSE_LOCATION
     */
    private void applyAllPermissions() {
        //申请必要权限
        if (checkPermissions()) {
            hasRermission = true;
            gotoNextActivity();
        } else {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE
            }, PERMISSIONS_REQUEST_CODE);
        }
    }
}