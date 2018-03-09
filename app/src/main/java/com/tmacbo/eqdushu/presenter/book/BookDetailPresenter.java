package com.tmacbo.eqdushu.presenter.book;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.book.BookDetailData;
import com.tmacbo.eqdushu.model.book.GetBookDetailModel;
import com.tmacbo.eqdushu.model.book.GetBookDetailRequest;
import com.tmacbo.eqdushu.ui.book.BookDetailView;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  15:28
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BookDetailPresenter
    extends BasePresenter<BookDetailView<BaseResponse<BookDetailData>>> {
    public BookDetailPresenter(BookDetailView<BaseResponse<BookDetailData>> booKdetailData) {
        attachView(booKdetailData);
    }

    public void getBookDetailInfo(BaseImpl baseImpl) {
        GetBookDetailRequest request = new GetBookDetailRequest();
        request.setIsbn(getView().getIsBn());
        request.setTermTyp("IOS");
        request.setType("reader");

        GetBookDetailModel.getInstance()
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
}
