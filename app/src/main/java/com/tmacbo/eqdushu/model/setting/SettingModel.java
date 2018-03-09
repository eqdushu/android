package com.tmacbo.eqdushu.model.setting;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BaseModel;
import io.reactivex.Observable;

/**
 * @Author tmacbo
 * @Since on 2017/11/28  02:06
 * @mail tang_bo@hotmail.com
 * @Description 设置model
 */

public class SettingModel extends BaseModel {
    public static SettingModel getInstance() {
        return getPresent(SettingModel.class);
    }

    public void executeModify(ModifyPswRequest request,
        CygBaseObserver<BaseResponse<String>> observer) {
        Observable observable = mServletApi.modifyPsw(request);
        toSubscribe(observable, observer);
    }

    public void executeReset(ModifyPswRequest request,
        CygBaseObserver<BaseResponse<String>> observer) {
        Observable observable = mServletApi.resetPsw(request);
        toSubscribe(observable, observer);
    }
}
