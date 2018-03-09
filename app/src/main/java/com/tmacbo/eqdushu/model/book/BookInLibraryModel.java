package com.tmacbo.eqdushu.model.book;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BaseModel;
import io.reactivex.Observable;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  17:11
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BookInLibraryModel extends BaseModel {
    public static BookInLibraryModel getInstance() {
        return getPresent(BookInLibraryModel.class);
    }

    public void execute(GetBookDetailRequest request,
        CygBaseObserver<BaseResponse<BookDetailData>> observer) {
        Observable observable = mServletApi.bookInLibrary(request);
        toSubscribe(observable, observer);
    }

    public void executeBorrow(GetBookDetailRequest request,
        CygBaseObserver<BaseResponse<BookDetailData>> observer) {
        Observable observable = mServletApi.borrowBook(request);
        toSubscribe(observable, observer);
    }

    public void executeBack(GetBookDetailRequest request,
        CygBaseObserver<BaseResponse<BookDetailData>> observer) {
        Observable observable = mServletApi.backBook(request);
        toSubscribe(observable, observer);
    }
}