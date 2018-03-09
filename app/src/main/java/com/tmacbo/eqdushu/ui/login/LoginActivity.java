package com.tmacbo.eqdushu.ui.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.util.FRToast;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.model.login.LoginData;
import com.tmacbo.eqdushu.presenter.login.LoginPresenter;
import com.tmacbo.eqdushu.ui.register.RegisterActivity;
import com.tmacbo.eqdushu.ui.settting.ModifyPassWordActivity;

/**
 * @Author tmacbo
 * @Since on 2017/11/16  15:43
 * @mail tang_bo@hotmail.com
 * @Description 登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter>
    implements LoginView<BaseResponse<LoginData>>, View.OnClickListener {

    @BindView(R.id.al_et_user_name)
    TextInputEditText alEtUserName;

    @BindView(R.id.al_et_password)
    TextInputEditText alEtPassword;

    @BindView(R.id.textView2Register)
    TextView textView2Register;

    @BindView(R.id.textViewFogetPsw)
    TextView textViewFogetPsw;

    @BindView(R.id.al_btn_login)
    Button mButtonLogin;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        alEtUserName.setText("13973003026");
        alEtPassword.setText("123321");
    }

    @Override
    protected void initData() {
        setTitle("登录");
        textView2Register.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        textViewFogetPsw.setOnClickListener(this);
    }

    @Override
    public String getUserName() {
        return alEtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return alEtPassword.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        if (view == textView2Register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, 100);
        } else if (view == mButtonLogin) {
            if (TextUtils.isEmpty(getUserName())) {
                alEtPassword.setError("用户名不能为空");
                return;
            }
            if (TextUtils.isEmpty(getPassword())) {
                alEtPassword.setError("密码不能为空");
                return;
            }
            mPresenter.getUserInfo(this);
        } else if (view == textViewFogetPsw) {
            Intent intent = new Intent(this, ModifyPassWordActivity.class);
            intent.putExtra("from", "reset");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    public void onRequestSuccessData(BaseResponse<LoginData> data) {
        if (data == null) {
            return;
        }
        mPresenter.toMainActivity(this, data);
        this.finish();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            this.finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}