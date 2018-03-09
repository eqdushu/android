package com.tmacbo.eqdushu.ui.settting;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.presenter.setting.SettingPresenter;

/**
 * @Author tmacbo
 * @Since on 2017/11/27  23:25
 * @mail tang_bo@hotmail.com
 * @Description 忘记密码&修改密码
 */

public class ModifyPassWordActivity extends BaseActivity<SettingPresenter>
    implements SettingView, View.OnClickListener {
    private String from;
    @BindView(R.id.editModifyMobile)
    TextInputEditText editModifyMobile;

    @BindView(R.id.editPrePsw)
    TextInputEditText editPrePsw;

    @BindView(R.id.editNewPsw)
    TextInputEditText editNewPsw;

    @BindView(R.id.editNewPswComfrim)
    TextInputEditText editNewPswComfrim;

    @BindView(R.id.editModifySmsCode)
    TextInputEditText editModifySmsCode;

    @BindView(R.id.textGetSmsCode)
    TextView textGetSmsCode;

    @BindView(R.id.btnComfrimModify)
    Button btnComfrimModify;

    @BindView(R.id.linearGetSms)
    LinearLayout linearGetSms;

    @BindView(R.id.layoutMoblie)
    TextInputLayout layoutMoblie;

    @BindView(R.id.layoutNewPsw)
    TextInputLayout layoutNewPsw;

    @BindView(R.id.layoutPrePsw)
    TextInputLayout layoutPrePsw;

    @BindView(R.id.layoutPswComfrim)
    TextInputLayout layoutPswComfrim;

    @Override
    protected int layoutRes() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initView() {
        from = getIntent().getStringExtra("from");
        if ("modify".equals(from)) {
            setTitle("修改密码");
            linearGetSms.setVisibility(View.GONE);
            layoutMoblie.setVisibility(View.GONE);

            layoutNewPsw.setVisibility(View.VISIBLE);
            layoutPrePsw.setVisibility(View.VISIBLE);
            layoutPswComfrim.setVisibility(View.VISIBLE);

            btnComfrimModify.setText("确认修改");
        } else {
            setTitle("忘记密码");
            linearGetSms.setVisibility(View.VISIBLE);
            layoutMoblie.setVisibility(View.VISIBLE);

            layoutNewPsw.setVisibility(View.VISIBLE);
            layoutPrePsw.setVisibility(View.GONE);
            layoutPswComfrim.setVisibility(View.GONE);

            btnComfrimModify.setText("立即重置");
        }
    }

    @Override
    protected void initData() {
        btnComfrimModify.setOnClickListener(this);
        textGetSmsCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnComfrimModify) {
            mPresenter.modifyPsw(this);
        } else if (view == textGetSmsCode) {

        }
    }

    @Override
    public void onRequestSuccessData(Object data) {

    }

    @Override
    public void onRequestFaliure(String rspInf) {

    }

    @Override
    public String getMobile() {
        return null;
    }

    @Override
    public String getSmsCode() {
        return null;
    }

    @Override
    public String getPrePassWord() {
        return null;
    }

    @Override
    public String getNewPassWord() {
        return null;
    }
}
