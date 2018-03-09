package com.tmacbo.eqdushu.model.register;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BaseModel;
import com.tmacbo.eqdushu.model.GetSmsCodeRequest;
import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import io.reactivex.Observable;

/**
 * @Author tmacbo
 * @Since on 2017/10/24  15:27
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class RegisterModel extends BaseModel {
    public static RegisterModel getInstance() {
        return getPresent(RegisterModel.class);
    }

    public void execute(GetSmsCodeRequest request, CygBaseObserver<BaseResponse<String>> observer) {
        Observable observable = mServletApi.getSmsCode(request);
        toSubscribe(observable, observer);
    }

    public void executeRegister(RegisterRequest request,
        CygBaseObserver<BaseResponse<String>> observer) {
        Observable observable = mServletApi.register(request);
        toSubscribe(observable, observer);
    }

    public void executeCreate(CompanyDataRequest request,
        CygBaseObserver<BaseResponse<String>> observer) {
        Observable observable = mServletApi.setUserType(request);
        toSubscribe(observable, observer);
    }
}
