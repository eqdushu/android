package com.tmacbo.eqdushu.presenter;

import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import com.tmacbo.eqdushu.model.register.RegisterModel;
import com.tmacbo.eqdushu.ui.register.SetTypeView;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;

/**
 * @Author tmacbo
 * @Since on 2017/10/23  16:58
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class SetTypePresenter extends BasePresenter<SetTypeView<BaseResponse>> {
    public SetTypePresenter(SetTypeView registerView) {
        attachView(registerView);
    }

    public void setCompany(BaseImpl baseImpl) {
        CompanyDataRequest request = new CompanyDataRequest();
        request.setCompanyCode(getView().getCompanyCode());
        request.setCompanyName(getView().getCompanyName());
        request.setUserName(getView().getUserName());
        request.setType("admin");

        RegisterModel.getInstance()
                     .executeCreate(request,
                                    new CygBaseObserver<BaseResponse<String>>(baseImpl, "正在创建企业") {
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

    public void addCompany(BaseImpl baseImpl) {
        CompanyDataRequest request = new CompanyDataRequest();
        request.setCompanyCode(getView().getCompanyCode());
        request.setUserName(getView().getUserName());
        request.setType("reader");

        RegisterModel.getInstance()
                     .executeCreate(request,
                                    new CygBaseObserver<BaseResponse<String>>(baseImpl, "正在加入企业") {
                                        @Override
                                        protected void onBaseNext(BaseResponse<String> data) {
                                            if (data.isRequestSuccess()) {
                                                getView().onSuccessToReader(data);
                                            } else {
                                                getView().onRequestFaliure(data.getRspInf());
                                            }
                                        }
                                    });
    }

}
