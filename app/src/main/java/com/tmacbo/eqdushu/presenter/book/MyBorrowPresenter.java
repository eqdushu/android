package com.tmacbo.eqdushu.presenter.book;

import cn.share.jack.cyghttp.BaseResponse;
import cn.share.jack.cyghttp.callback.BaseImpl;
import cn.share.jack.cyghttp.callback.CygBaseObserver;
import com.tmacbo.eqdushu.common.base.BasePresenter;
import com.tmacbo.eqdushu.model.book.GetMyBorrowData;
import com.tmacbo.eqdushu.model.book.GetMyBorrowRequest;
import com.tmacbo.eqdushu.model.book.MyBorrowModel;
import com.tmacbo.eqdushu.ui.main.BorrowView;
import java.util.List;

/**
 * @Author tmacbo
 * @Since on 2017/10/12  14:33
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class MyBorrowPresenter
    extends BasePresenter<BorrowView<List<GetMyBorrowData>>> {

    public MyBorrowPresenter(BorrowView<List<GetMyBorrowData>> borrowData) {
        attachView(borrowData);
    }

    public void getMyBorrowData(BaseImpl baseImpl) {
        GetMyBorrowRequest request = new GetMyBorrowRequest();
        request.setPageNo("1");
        request.setPageNum("10");
        MyBorrowModel.getInstance()
                     .executeMyBorrow(request,
                                      new CygBaseObserver<BaseResponse<List<GetMyBorrowData>>>(
                                          baseImpl) {
                                          @Override
                                          protected void onBaseNext(
                                              BaseResponse<List<GetMyBorrowData>> data) {
                                              if (data.isRequestSuccess()) {
                                                  getView().onRequestSuccessData(data.getData());
                                              } else {
                                                  getView().onRequestFaliure(data.getRspInf());
                                              }
                                          }
                                      });
    }
}