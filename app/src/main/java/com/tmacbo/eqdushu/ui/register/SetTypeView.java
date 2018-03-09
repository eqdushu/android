package com.tmacbo.eqdushu.ui.register;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * @Author tmacbo
 * @Since on 2017/10/26  16:33
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public interface SetTypeView<T> extends BaseRequestContract<T> {
    String getCompanyName();

    String getCompanyCode();

    String getUserName();

    void onSuccessToReader(T data);
}
