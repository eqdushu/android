package com.tmacbo.eqdushu.presenter.book;

import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.book.BookDetailData;
import com.tmacbo.eqdushu.model.book.BookInLibraryModel;
import com.tmacbo.eqdushu.model.book.GetBookDetailRequest;
import com.tmacbo.eqdushu.ui.book.BookDetailView;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  17:10
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BookInLibraryPresenter extends BasePresenter<BookDetailView<BaseResponse>> {
    public BookInLibraryPresenter(BookDetailView<BaseResponse> booKdetailData) {
        attachView(booKdetailData);
    }

    public void getBookDetailInfo(BaseImpl baseImpl) {
        GetBookDetailRequest request = new GetBookDetailRequest();
        request.setIsbn(getView().getIsBn());

        BookInLibraryModel.getInstance()
                          .execute(request,
                                   new CygBaseObserver<BaseResponse<BookDetailData>>(baseImpl,
                                                                                     "请稍后") {
                                       @Override
                                       protected void onBaseNext(
                                           BaseResponse<BookDetailData> data) {
                                           if (data.isRequestSuccess()) {
                                               getView().onRequestSuccessData(data);
                                           } else {
                                               getView().onRequestFaliure(data.getRspInf());
                                           }
                                       }
                                   });
    }

    public void bookBorrow(BaseImpl baseImpl) {
        GetBookDetailRequest request = new GetBookDetailRequest();
        request.setIsbn(getView().getIsBn());

        BookInLibraryModel.getInstance()
                          .executeBorrow(request,
                                   new CygBaseObserver<BaseResponse<BookDetailData>>(baseImpl,
                                                                                     "请稍后") {
                                       @Override
                                       protected void onBaseNext(
                                           BaseResponse<BookDetailData> data) {
                                           if (data.isRequestSuccess()) {
                                               getView().onRequestSuccessData(data);
                                           } else {
                                               getView().onRequestFaliure(data.getRspInf());
                                           }
                                       }
                                   });
    }

    public void backBorrow(BaseImpl baseImpl) {
        GetBookDetailRequest request = new GetBookDetailRequest();
        request.setIsbn(getView().getIsBn());

        BookInLibraryModel.getInstance()
                .executeBack(request,
                        new CygBaseObserver<BaseResponse<BookDetailData>>(baseImpl,
                                "请稍后") {
                            @Override
                            protected void onBaseNext(
                                    BaseResponse<BookDetailData> data) {
                                if (data.isRequestSuccess()) {
                                    getView().onRequestSuccessData(data);
                                } else {
                                    getView().onRequestFaliure(data.getRspInf());
                                }
                            }
                        });
    }
}
