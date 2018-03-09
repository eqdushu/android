package com.tmacbo.eqdushu.presenter.login;

import android.content.Context;
import android.content.Intent;
import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.share.jack.greendao.bean.UserInfo;
import com.tmacbo.eqdushu.activity.AdminMainActivity;
import com.tmacbo.eqdushu.common.CommonConfig;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.login.LoginData;
import com.tmacbo.eqdushu.model.login.LoginModel;
import com.tmacbo.eqdushu.model.login.LoginRequest;
import com.tmacbo.eqdushu.ui.login.LoginView;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.ReaderMainActivity;
import com.tmacbo.eqdushu.ui.register.CreateCompanyActivity;

/**
 * Created by jack on 2017/6/13
 */

public class LoginPresenter extends BasePresenter<LoginView<BaseResponse<LoginData>>> {

    public LoginPresenter(LoginView<BaseResponse<LoginData>> loginView) {
        attachView(loginView);
    }

    public void getUserInfo(BaseImpl baseImpl) {
        LoginRequest request = new LoginRequest();
        request.setPasswd(getView().getPassword());
        request.setPhone(getView().getUserName());

        LoginModel.getInstance()
                  .execute(request, new CygBaseObserver<BaseResponse<LoginData>>(baseImpl, "正在登录") {
                      @Override
                      protected void onBaseNext(BaseResponse<LoginData> data) {

                          if (data.isRequestSuccess()) {
                              UserDao.getInstance().deleteAll(UserInfo.class);

                              UserInfo userInfo = new UserInfo();
                              userInfo.setId(data.getData().getUserId());
                              userInfo.setUserType(data.getData().getUserType());
                              userInfo.setUsername(getView().getUserName());
                              userInfo.setToken(data.getData().getxAuthToken());
                              userInfo.setCompany(data.getData().getCompany());
                              userInfo.setCompanyId(data.getData().getCompanyId());
                              CommonConfig.USER_TYPE = data.getData().getUserType();

                              UserDao.getInstance().insertObject(userInfo);

                              getView().onRequestSuccessData(data);
                          } else {
                              getView().onRequestFaliure(data.getRspInf());
                          }
                      }
                  });
    }

    public void toMainActivity(Context context, BaseResponse<LoginData> data) {
        if ("reader".equals(data.getData().getUserType())) {
            context.startActivity(new Intent(context, ReaderMainActivity.class));
        } else if ("admin".equals(data.getData().getUserType())) {
            context.startActivity(new Intent(context, AdminMainActivity.class));
        } else if ("guest".equals(data.getData().getUserType())) {
            context.startActivity(new Intent(context, CreateCompanyActivity.class));
        }
    }
}