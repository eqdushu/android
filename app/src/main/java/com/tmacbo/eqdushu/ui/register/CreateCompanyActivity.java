package com.tmacbo.eqdushu.ui.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import cn.share.jack.cyghttp.BaseResponse;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.model.login.LoginData;
import com.tmacbo.eqdushu.presenter.login.LoginPresenter;
import com.tmacbo.eqdushu.ui.login.LoginView;

/**
 * @Author tmacbo
 * @Since on 2017/10/23  15:56
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class CreateCompanyActivity extends BaseActivity<LoginPresenter>
    implements LoginView<BaseResponse<LoginData>>, View.OnClickListener {
    @BindView(R.id.btnGo2Create)
    Button btnGo2Create;

    @BindView(R.id.textGo2AddCompany)
    TextView textGo2AddCompany;

    private String userName;
    private String passWord;

    private Intent mIntent;
    private int ADD_TYPE = 0;
    private int CREATE_TYPE = 1;
    private boolean isRegister;

    @Override
    protected int layoutRes() {
        return R.layout.activity_create_company;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("加入企业");
    }

    @Override
    protected void initData() {
        userName = getIntent().getStringExtra("userName");
        passWord = getIntent().getStringExtra("passWord");
        isRegister = getIntent().getBooleanExtra("isRegister", false);
        if (isRegister) {
            mPresenter.getUserInfo(this);
        }
        btnGo2Create.setOnClickListener(this);
        textGo2AddCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnGo2Create) {
            mIntent = new Intent(this, SetUserTypeActivity.class);
            mIntent.putExtra("type", CREATE_TYPE);
            startActivityForResult(mIntent, 100);
        } else if (view == textGo2AddCompany) {
            mIntent = new Intent(this, SetUserTypeActivity.class);
            mIntent.putExtra("type", ADD_TYPE);
            startActivityForResult(mIntent, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100 && resultCode == RESULT_OK) {
            this.finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestSuccessData(BaseResponse<LoginData> data) {

    }

    @Override
    public void onRequestFaliure(String rspInf) {

    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return passWord;
    }
}
