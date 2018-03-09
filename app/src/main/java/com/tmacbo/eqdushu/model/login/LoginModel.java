package com.tmacbo.eqdushu.model.login;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BaseModel;
import com.tmacbo.eqdushu.model.register.RegisterRequest;
import com.tmacbo.eqdushu.ui.login.UserDao;
import io.reactivex.Observable;

/**
 * Created by jack on 2017/6/13
 */

public class LoginModel extends BaseModel {

    public static LoginModel getInstance() {
        return getPresent(LoginModel.class);
    }

    public void execute(LoginRequest request, CygBaseObserver<BaseResponse<LoginData>> observer) {
        Observable observable = mServletApi.login(request);
        toSubscribe(observable, observer);
    }
}