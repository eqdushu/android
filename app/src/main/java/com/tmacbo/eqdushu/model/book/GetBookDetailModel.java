package com.tmacbo.eqdushu.model.book;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BaseModel;
import io.reactivex.Observable;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  16:11
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class GetBookDetailModel extends BaseModel {
    public static GetBookDetailModel getInstance() {
        return getPresent(GetBookDetailModel.class);
    }

    public void execute(GetBookDetailRequest request,
        CygBaseObserver<BaseResponse<BookDetailData>> observer) {
        Observable observable = mServletApi.getBookDetails(request);
        toSubscribe(observable, observer);
    }
}
