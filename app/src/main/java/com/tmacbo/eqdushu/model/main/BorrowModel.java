package com.tmacbo.eqdushu.model.main;

import com.tmacbo.eqdushu.common.base.BaseModel;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.model.book.CompanyBorrowRequest;

import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import java.util.List;

import cn.share.jack.cyghttp.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @Author tmacbo
 * @Since on 2017/11/9 00:43
 * @mail tang_bo@cloudfin.com
 * @Description TODO
 */
public class BorrowModel extends BaseModel {
    public static BorrowModel getInstance() {
        return getPresent(BorrowModel.class);
    }
    public void executeBorrowInfo(CompanyDataRequest request,
                                  Observer<BaseResponse<List<BorrowInfoData>>> observer) {
        Observable observable = mServletApi.getCompanyBrowDetail(request);
        toSubscribe(observable, observer);
    }
}
