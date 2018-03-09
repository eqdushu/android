package com.tmacbo.eqdushu.presenter.setting;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.setting.ModifyPswRequest;
import com.tmacbo.eqdushu.model.setting.SettingModel;
import com.tmacbo.eqdushu.ui.settting.SettingView;

/**
 * @Author tmacbo
 * @Since on 2017/11/27  23:20
 * @mail tang_bo@hotmail.com
 * @Description 设置页面模块处理
 */

public class SettingPresenter extends BasePresenter<SettingView> {
    public SettingPresenter(SettingView view) {
        attachView(view);
    }

    public void modifyPsw(BaseImpl baseImpl) {
        ModifyPswRequest request = new ModifyPswRequest();
        request.setNewPass(getView().getNewPassWord());
        request.setPhone(getView().getMobile());
        request.setSmsCode(getView().getSmsCode());
        SettingModel.getInstance()
                    .executeModify(request, new CygBaseObserver<BaseResponse<String>>() {
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
}
