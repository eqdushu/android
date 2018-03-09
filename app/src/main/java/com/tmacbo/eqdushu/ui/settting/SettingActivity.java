package com.tmacbo.eqdushu.ui.settting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.share.jack.greendao.bean.UserInfo;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.CommonConfig;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.ui.login.LoginActivity;
import com.tmacbo.eqdushu.ui.login.UserDao;

/**
 * @Author tmacbo
 * @Since on 2017/11/16  15:43
 * @mail tang_bo@hotmail.com
 * @Description 设置
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.textViewlogout)
    TextView textViewlogout;

    @BindView(R.id.textViewAppVersion)
    TextView textViewAppVersion;

    @BindView(R.id.fl_modify_password)
    FrameLayout mFrameLayoutModify;

    @BindView(R.id.fl_set_msg)
    FrameLayout mFrameLayoutSetMsg;

    @BindView(R.id.fl_set_about)
    FrameLayout mFrameLayoutAbout;

    @Override
    protected int layoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setTitle("设置");
    }

    @Override
    protected void initData() {
        addBackAction();

        textViewlogout.setOnClickListener(this);
        mFrameLayoutModify.setOnClickListener(this);
        mFrameLayoutSetMsg.setOnClickListener(this);
        mFrameLayoutAbout.setOnClickListener(this);

        textViewAppVersion.setText(CommonConfig.getAppVersion(this));
    }

    @Override
    public void onClick(View view) {
        if (view == textViewlogout) {
            showComfrimDialog();
        } else if (view == mFrameLayoutAbout) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (view == mFrameLayoutSetMsg) {

        } else if (view == mFrameLayoutModify) {
            Intent intent = new Intent(this, ModifyPassWordActivity.class);
            intent.putExtra("from", "modify");
            startActivity(intent);
        }
    }

    private void quit2Login() {
        startActivity(new Intent(this, LoginActivity.class));
        UserDao.getInstance().deleteAll(UserInfo.class);
        setResult(RESULT_OK);
        this.finish();
    }

    private void showComfrimDialog() {
        new MaterialDialog.Builder(this).content("是否退出" + getString(R.string.app_name))
                                        .negativeText("取消")
                                        .positiveText("确定")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog,
                                                @NonNull DialogAction which) {
                                                quit2Login();
                                            }
                                        })
                                        .show();
    }
}
