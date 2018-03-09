package com.tmacbo.eqdushu.presenter.main;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.share.jack.greendao.bean.UserInfo;
import com.tmacbo.eqdushu.common.LogUtils;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.common.base.BaseRequest;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import com.tmacbo.eqdushu.model.main.BookInfo;
import com.tmacbo.eqdushu.model.main.MainModel;
import com.tmacbo.eqdushu.model.personalInfo.PersonalInfoModel;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.ui.login.UserDao;
import com.tmacbo.eqdushu.ui.main.MainView;
import java.util.List;

/**
 * Created by jack on 2017/6/14
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void getBookData(BaseImpl baseImpl) {
        MainModel.getInstance()
                 .execute(new BaseRequest(),
                          new CygBaseObserver<BaseResponse<List<BookInfo>>>(baseImpl) {
                              @Override
                              protected void onBaseNext(BaseResponse<List<BookInfo>> data) {
                                  if (data.isRequestSuccess()) {
                                      getView().onRequestSuccessData(data.getData());
                                  } else if (data.isTokenError()) {
                                      UserDao.getInstance().deleteAll(UserInfo.class);
                                      getView().getTokenEorro();
                                  } else {
                                      getView().onRequestFaliure(data.getRspInf());
                                  }
                              }
                          });
    }

    public void getBorrowData(BaseImpl baseImpl) {
        CompanyDataRequest request = new CompanyDataRequest();
        if (UserDao.getInstance().getUserInfo() == null) {
            return;
        }
        request.setCompanyId(UserDao.getInstance().getUserInfo().getCompanyId());
        MainModel.getInstance()
                 .executeBorrowInfo(request,
                                    new CygBaseObserver<BaseResponse<List<BorrowInfoData>>>(
                                        baseImpl) {
                                        @Override
                                        protected void onBaseNext(
                                            BaseResponse<List<BorrowInfoData>> data) {
                                            if (data.isRequestSuccess()) {
                                                getView().onRequestSuccessData(data.getData());
                                            } else if (data.isTokenError()) {
                                                UserDao.getInstance().deleteAll(UserInfo.class);
                                                getView().getTokenEorro();
                                            } else {
                                                getView().onRequestFaliure(data.getRspInf());
                                            }
                                        }
                                    });
    }

    public void getRewardGas(BaseImpl baseImpl) {

        MainModel.getInstance()
                         .executeGetReward(new BaseRequest(), new CygBaseObserver<BaseResponse<RewardInfoData>>(
                             baseImpl, "请稍后") {
                             @Override
                             protected void onBaseNext(BaseResponse<RewardInfoData> data) {
                                 if (data.isRequestSuccess()) {
                                     getView().getRewardGas(data.getData());
                                 } else {
                                     getView().onRequestFaliure(data.getRspInf());
                                 }
                             }
                             @Override
                             public void onError(Throwable e) {
                                 LogUtils.debug(e.getMessage());
                                 super.onError(e);
                             }
                         });

    }
}