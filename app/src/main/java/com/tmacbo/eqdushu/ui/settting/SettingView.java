package com.tmacbo.eqdushu.ui.settting;

import com.tmacbo.eqdushu.common.base.BaseRequestContract;

/**
 * @Author tmacbo
 * @Since on 2017/11/27  23:22
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public interface SettingView<T> extends BaseRequestContract<T> {
    String getMobile();

    String getSmsCode();

    String getPrePassWord();

    String getNewPassWord();
}
