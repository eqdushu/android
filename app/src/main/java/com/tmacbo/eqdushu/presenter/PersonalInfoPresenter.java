package com.tmacbo.eqdushu.presenter;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.LogUtils;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.common.base.BaseRequest;
import com.tmacbo.eqdushu.model.main.BookInfo;
import com.tmacbo.eqdushu.model.main.MainModel;
import com.tmacbo.eqdushu.model.personalInfo.PersonalInfoModel;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.ui.main.PersonalInfoView;
import java.util.List;

/**
 * @Author tmacbo
 * @Since on 2018/3/7  20:53
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class PersonalInfoPresenter
    extends BasePresenter<PersonalInfoView> {
    public PersonalInfoPresenter(PersonalInfoView<RewardInfoData> rewardInfo) {
        attachView(rewardInfo);
    }

    public void getRewardInfo(BaseImpl baseImpl) {

        MainModel.getInstance()
                         .executeRewardInfo(new BaseRequest(),
                                new CygBaseObserver<BaseResponse<RewardInfoData>>(baseImpl, "请稍后") {
                                    @Override
                                    protected void onBaseNext(BaseResponse<RewardInfoData> data) {
                                        if (data.isRequestSuccess()) {
                                            getView().onRequestSuccessData(data.getData());
                                        } else {
                                            getView().onRequestFaliure(data.getRspInf());
                                        }
                                    }
                                });
    }

    public void getRewardGas(BaseImpl baseImpl) {

        PersonalInfoModel.getInstance()
                         .executeGetReward(new BaseRequest(), new CygBaseObserver<BaseResponse<RewardInfoData>>(
                             baseImpl, "请稍后") {
                             @Override
                             protected void onBaseNext(BaseResponse<RewardInfoData> data) {
                                 if (data.isRequestSuccess()) {
                                     getView().onRequestSuccessData(data.getData());
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

    public void getTest(BaseImpl baseImpl) {

        MainModel.getInstance()
                 .execute(new BaseRequest(),
                          new CygBaseObserver<BaseResponse<List<BookInfo>>>(baseImpl, "请稍后") {
                                               @Override
                                               protected void onBaseNext(BaseResponse<List<BookInfo>> data) {
                                                   if (data.isRequestSuccess()) {
                                                       getView().onRequestSuccessData(data);
                                                   } else {
                                                       getView().onRequestFaliure(data.getRspInf());
                                                   }
                                               }


                          });
    }
}
