package com.tmacbo.eqdushu.model.personalInfo;

import cn.share.jack.cyghttp.BaseResponse;
import com.tmacbo.eqdushu.common.base.BaseModel;
import com.tmacbo.eqdushu.common.base.BaseRequest;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @Author tmacbo
 * @Since on 2018/3/7  22:51
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class PersonalInfoModel extends BaseModel {
    public static PersonalInfoModel getInstance() {
        return getPresent(PersonalInfoModel.class);
    }

    public void executeRewardInfo(BaseRequest request,
        Observer<BaseResponse<RewardInfoData>> observer) {
        Observable observable = mServletApi.getAccountGas(request);
        toSubscribe(observable, observer);
    }

    public void executeGetReward(BaseRequest request,
        Observer<BaseResponse<RewardInfoData>> observer) {
        Observable observable = mServletApi.getRewardGas(request);
        toSubscribe(observable, observer);
    }
}
