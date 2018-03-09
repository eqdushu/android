package com.tmacbo.eqdushu.ui.register;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.util.FRToast;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.presenter.RegisterPresenter;

/**
 * @Author tmacbo
 * @Since on 2017/10/23  15:36
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter>
    implements RegisterView, View.OnClickListener {

    @BindView(R.id.editRegisterMobile)
    TextInputEditText editRegisterMobile;

    @BindView(R.id.editRegisterPsw)
    TextInputEditText editRegisterPsw;

    @BindView(R.id.editRegisterSmsCode)
    TextInputEditText editRegisterSmsCode;

    @BindView(R.id.textGetSmsCode)
    TextView textGetSmsCode;

    @BindView(R.id.btnGo2Register)
    Button btnGo2Register;
    //计时
    private static int time = 60;
    private static boolean isNetExit = false;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        setTitle("立即注册");
    }

    @Override
    protected void initData() {
        textGetSmsCode.setOnClickListener(this);
        btnGo2Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == textGetSmsCode) {
            if (TextUtils.isEmpty(getMobile())) {
                editRegisterMobile.setError("手机号不能为空");
                return;
            }

            mPresenter.getSmsCode(this);
        } else if (v == btnGo2Register) {
            if (TextUtils.isEmpty(getSmsCode())) {
                editRegisterSmsCode.setError("验证码不能为空");
                return;
            } else if (TextUtils.isEmpty(getPassWord())) {
                editRegisterSmsCode.setError("密码不能为空");
                return;
            }
            mPresenter.register(this);
            //Intent intent = new Intent(this, SetPasswordActivity.class);
            //intent.putExtra("smsCode", getSmsCode());
            //intent.putExtra("mobile", getMobile());
            //startActivityForResult(intent, 100);
        }
    }

    @Override
    public String getMobile() {
        return editRegisterMobile.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return editRegisterSmsCode.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return editRegisterPsw.getText().toString().trim();
    }

    @Override
    public void getRegisterSuccess(Object data) {
        FRToast.showToastSafe("注册成功");
        setResult(RESULT_OK);

        Intent intent = new Intent(this, CreateCompanyActivity.class);
        intent.putExtra("isRegister", true);
        intent.putExtra("userName", editRegisterMobile.getText().toString().trim());
        intent.putExtra("passWord", editRegisterPsw.getText().toString().trim());

        startActivity(intent);

        this.finish();
    }

    @Override
    public void onRequestSuccessData(Object data) {
        FRToast.showToastSafe("验证码下发成功");
        refreshCodeBtn();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            this.finish();
        }
    }

    //刷新验证码
    private void refreshCodeBtn() {
        //禁止点击
        time = 60;
        isNetExit = false;
        textGetSmsCode.setEnabled(false);
        final int fontColor = textGetSmsCode.getCurrentTextColor();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            //                            String tip = time + "秒后重发";
                            String tip = "获取验证码(" + time + ")";
                            textGetSmsCode.setText(tip);
                            textGetSmsCode.setTextColor(getResources().getColor(R.color.w4));
                        }
                    });
                    if (isNetExit) {
                        //网络错误、验证码发送成功,改变isExit = true;
                        break;
                    }
                    //延时
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                    time--;
                    if (time <= 0) {
                        break;
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //更新UI
                        String tip = "获取验证码";
                        textGetSmsCode.setText(tip);
                        textGetSmsCode.setEnabled(true);
                        textGetSmsCode.setTextColor(fontColor);
                    }
                });
            }
        }.start();
    }
}
