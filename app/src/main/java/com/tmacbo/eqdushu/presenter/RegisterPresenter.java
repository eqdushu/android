package com.tmacbo.eqdushu.presenter;

import android.content.Context;
import android.content.Intent;
import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.GetSmsCodeRequest;
import com.tmacbo.eqdushu.model.register.RegisterModel;
import com.tmacbo.eqdushu.model.register.RegisterRequest;
import com.tmacbo.eqdushu.ui.main.ReaderMainActivity;
import com.tmacbo.eqdushu.ui.register.RegisterView;

/**
 * @Author tmacbo
 * @Since on 2017/10/23  16:58
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class RegisterPresenter extends BasePresenter<RegisterView<BaseResponse>> {
    public RegisterPresenter(RegisterView registerView) {
        attachView(registerView);
    }

    public void getSmsCode(BaseImpl baseImpl) {
        GetSmsCodeRequest request = new GetSmsCodeRequest();
        request.setMblNo(getView().getMobile());
        request.setSmsTyp("R");

        RegisterModel.getInstance()
                     .execute(request,
                              new CygBaseObserver<BaseResponse<String>>(baseImpl, "正在请求验证码") {
                                  @Override
                                  protected void onBaseNext(BaseResponse<String> data) {
                                      if (data.isRequestSuccess()) {
                                          getView().onRequestSuccessData(data);
                                      } else {
                                          getView().onRequestFaliure(data.getRspInf());
                                      }
                                  }
                              });
    }

    public void register(BaseImpl baseImpl) {
        RegisterRequest request = new RegisterRequest();
        request.setPhone(getView().getMobile());
        request.setPasswd(getView().getPassWord());
        request.setSmsCode(getView().getSmsCode());

        RegisterModel.getInstance()
                     .executeRegister(request, new CygBaseObserver<BaseResponse<String>>(baseImpl,
                                                                                         "正在提交注册") {
                         @Override
                         protected void onBaseNext(BaseResponse<String> data) {
                             if (data.isRequestSuccess()) {
                                 getView().getRegisterSuccess(data);
                             } else {
                                 getView().onRequestFaliure(data.getRspInf());
                             }
                         }
                     });
    }

    public void toMainActivity(Context context) {
        context.startActivity(new Intent(context, ReaderMainActivity.class));
    }
}
