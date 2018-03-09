package com.tmacbo.eqdushu.ui.login;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * Created by jack on 2017/6/13
 */

public interface LoginView<T> extends BaseRequestContract<T> {

    String getUserName();

    String getPassword();
}