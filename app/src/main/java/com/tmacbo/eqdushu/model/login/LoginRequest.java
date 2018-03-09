package com.tmacbo.eqdushu.model.login;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/8  16:39
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class LoginRequest extends BaseRequest {
    private String phone;
    private String passwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
