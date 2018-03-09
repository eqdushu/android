package com.tmacbo.eqdushu.ui.main;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * @Author tmacbo
 * @Since on 2017/10/12  14:34
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public interface BorrowView<T> extends BaseRequestContract<T> {

    void getBookDataFailure(Throwable t);
}

