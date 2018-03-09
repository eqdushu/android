package com.tmacbo.eqdushu.ui.register;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * @Author tmacbo
 * @Since on 2017/10/23  15:36
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public interface RegisterView<T> extends BaseRequestContract<T> {
    String getMobile();

    String getSmsCode();

    String getPassWord();

    void getRegisterSuccess(T data);
}
