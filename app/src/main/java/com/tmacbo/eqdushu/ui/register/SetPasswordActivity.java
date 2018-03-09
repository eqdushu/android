package com.tmacbo.eqdushu.ui.register;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import cn.share.jack.cyghttp.util.FRToast;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.presenter.RegisterPresenter;

/**
 * @Author tmacbo
 * @Since on 2017/10/24  16:40
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class SetPasswordActivity extends BaseActivity<RegisterPresenter>
    implements RegisterView, View.OnClickListener {

    @BindView(R.id.editSetPswConfrim)
    TextInputEditText editSetPswConfrim;

    @BindView(R.id.editSetPsw)
    TextInputEditText editSetPsw;

    @BindView(R.id.btnConfrimRegister)
    Button btnConfrimRegister;

    private String mobile;
    private String smsCode;
    private String passWord;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_password_set;
    }

    @Override
    protected void initView() {

        smsCode = getIntent().getStringExtra("smsCode");
        mobile = getIntent().getStringExtra("mobile");
    }

    @Override
    protected void initData() {
        btnConfrimRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnConfrimRegister) {
            if (TextUtils.isEmpty(editSetPsw.getText())) {
                editSetPsw.setError("请输入密码");
                return;
            } else if (TextUtils.isEmpty(getPassWord())) {
                editSetPswConfrim.setError("请再次输入密码");
                return;
            } else if (!getPassWord().equals(editSetPsw.getText().toString().trim())) {
                editSetPswConfrim.setError("两次输入密码不一致");
                return;
            }

            mPresenter.register(this);
        }
    }

    @Override
    public void onRequestSuccessData(Object data) {
        FRToast.showToastSafe("注册成功");
        setResult(RESULT_OK);
        startActivity(new Intent(this, CreateCompanyActivity.class));
        this.finish();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public String getSmsCode() {
        return smsCode;
    }

    @Override
    public String getPassWord() {
        return editSetPswConfrim.getText().toString().trim();
    }

    @Override
    public void getRegisterSuccess(Object data) {

    }
}
