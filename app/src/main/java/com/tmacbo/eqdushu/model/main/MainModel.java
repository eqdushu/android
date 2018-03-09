package com.tmacbo.eqdushu.model.main;

import cn.share.jack.cyghttp.BaseResponse;
import com.tmacbo.eqdushu.common.base.BaseModel;
import com.tmacbo.eqdushu.common.base.BaseRequest;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.model.book.GetMyBorrowRequest;
import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import io.reactivex.Observable;
import io.reactivex.Observer;
import java.util.List;

/**
 * Created by jack on 2017/6/14
 */

public class MainModel extends BaseModel {

    public static MainModel getInstance() {
        return getPresent(MainModel.class);
    }

    public void execute(BaseRequest request, Observer<BaseResponse<List<BookInfo>>> observer) {
        Observable observable = mServletApi.getLibrary(request);
        toSubscribe(observable, observer);
    }

    public void executeBorrowInfo(CompanyDataRequest request,
        Observer<BaseResponse<List<BorrowInfoData>>> observer) {
        Observable observable = mServletApi.getCompanyBrowDetail(request);
        toSubscribe(observable, observer);
    }

    public void executeMyBorrow(GetMyBorrowRequest request,
        Observer<BaseResponse<List<BookInfo>>> observer) {
        Observable observable = mServletApi.getReaderBrowDetail(request);
        toSubscribe(observable, observer);
    }

    public void executeGetReward(BaseRequest request,
        Observer<BaseResponse<RewardInfoData>> observer) {
        Observable observable = mServletApi.getRewardGas(request);
        toSubscribe(observable, observer);
    }
    public void executeRewardInfo(BaseRequest request,
        Observer<BaseResponse<RewardInfoData>> observer) {
        Observable observable = mServletApi.getAccountGas(request);
        toSubscribe(observable, observer);
    }
}
