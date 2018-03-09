package com.tmacbo.eqdushu.ui.main;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;

/**
 * Created by jack on 2017/6/14
 */

public interface MainView<T> extends BaseRequestContract<T> {

    void getBookDataFailure(Throwable t);

    void getRewardGas(RewardInfoData data);

    void getTokenEorro();
}