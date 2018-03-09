package com.tmacbo.eqdushu.ui.register;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.share.jack.greendao.bean.UserInfo;
import com.tmacbo.eqdushu.R;
import com.tmacbo.eqdushu.activity.AdminMainActivity;
import com.tmacbo.eqdushu.common.base.BaseActivity;
import com.tmacbo.eqdushu.presenter.SetTypePresenter;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.ReaderMainActivity;

import butterknife.BindView;
import cn.share.jack.cyghttp.util.FRToast;

/**
 * @Author tmacbo
 * @Since on 2017/10/26  15:53
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class SetUserTypeActivity extends BaseActivity<SetTypePresenter>
    implements SetTypeView, View.OnClickListener {
    @BindView(R.id.editCompanyName)
    TextInputEditText editCompanyName;

    @BindView(R.id.editCompanyCode)
    TextInputEditText editCompanyCode;

    @BindView(R.id.textCompanyIdTips)
    TextView textCompanyIdTips;

    @BindView(R.id.textInputCompanyName)
    TextInputLayout textInputCompanyName;

    @BindView(R.id.editUserName)
    TextInputEditText editUserName;

    @BindView(R.id.linearAddCompanyId)
    LinearLayout linearAddCompanyId;

    @BindView(R.id.btnCreateConfrim)
    Button btnCreateConfrim;

    private int ADD_TYPE = 0;
    private int CREATE_TYPE = 1;
    private int type;

    @Override
    protected SetTypePresenter createPresenter() {
        return new SetTypePresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_set_usertype;
    }

    @Override
    protected void initView() {
        btnCreateConfrim.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", -1);
        if (type == CREATE_TYPE) {
            textInputCompanyName.setVisibility(View.VISIBLE);
            linearAddCompanyId.setVisibility(View.VISIBLE);
            textCompanyIdTips.setVisibility(View.VISIBLE);
            btnCreateConfrim.setText("立即创建企业");
        } else if (type == ADD_TYPE) {
            textInputCompanyName.setVisibility(View.GONE);
            linearAddCompanyId.setVisibility(View.VISIBLE);
            textCompanyIdTips.setVisibility(View.GONE);
            btnCreateConfrim.setText("立即加入企业");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnCreateConfrim) {
            if (TextUtils.isEmpty(getCompanyCode())) {
                editCompanyCode.setError("请输入企业编号");
                return;
            }
            if (type == ADD_TYPE) {
                mPresenter.addCompany(this);
            } else if (type == CREATE_TYPE) {
                if (TextUtils.isEmpty(getCompanyName())) {
                    editCompanyName.setError("请输入企业全称");
                    return;
                }
                mPresenter.setCompany(this);
            }
        }
    }

    @Override
    public void onRequestSuccessData(Object data) {
        upDateUserType("admin");
        startActivity(new Intent(this, AdminMainActivity.class));
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void onRequestFaliure(String rspInf) {
        FRToast.showToastSafe(rspInf);
    }

    @Override
    public String getCompanyName() {
        return editCompanyName.getText().toString().trim();
    }

    @Override
    public String getCompanyCode() {
        return editCompanyCode.getText().toString().trim();
    }

    @Override
    public String getUserName() {
        return editUserName.getText().toString().trim();
    }

    @Override
    public void onSuccessToReader(Object data) {
        upDateUserType("reader");
        setResult(RESULT_OK);
        startActivity(new Intent(this, ReaderMainActivity.class));
        this.finish();
    }

    private void upDateUserType(String userType) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UserDao.getInstance().getUserId());
        userInfo.setUserType(userType);
        userInfo.setUsername(UserDao.getInstance().getUserInfo().getUsername());
        userInfo.setToken(UserDao.getInstance().getToken());
        UserDao.getInstance().deleteAll(UserInfo.class);
        UserDao.getInstance().insertObject(userInfo);
    }
}
