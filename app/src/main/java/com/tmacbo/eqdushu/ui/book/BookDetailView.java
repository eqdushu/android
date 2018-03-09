package com.tmacbo.eqdushu.ui.book;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  15:57
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public interface BookDetailView<T> extends BaseRequestContract<T> {
    String getIsBn();
}
