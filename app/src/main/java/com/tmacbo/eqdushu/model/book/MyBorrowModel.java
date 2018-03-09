package com.tmacbo.eqdushu.model.book;

import cn.share.jack.cyghttp.BaseResponse;
import com.tmacbo.eqdushu.common.base.BaseModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import java.util.List;

/**
 * @Author tmacbo
 * @Since on 2017/10/12  14:39
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class MyBorrowModel extends BaseModel {
    public static MyBorrowModel getInstance() {
        return getPresent(MyBorrowModel.class);
    }


    public void executeMyBorrow(GetMyBorrowRequest request,
        Observer<BaseResponse<List<GetMyBorrowData>>> observer) {
        Observable observable = mServletApi.getReaderBrowDetail(request);
        toSubscribe(observable, observer);
    }
}
