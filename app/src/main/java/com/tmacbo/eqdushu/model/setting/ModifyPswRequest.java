package com.tmacbo.eqdushu.model.setting;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/11/28  02:09
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class ModifyPswRequest extends BaseRequest {

    /**
     * newPass :
     * phone :
     * smsCode :
     */

    private String newPass;
    private String phone;
    private String smsCode;

    public String getNewPass() { return newPass;}

    public void setNewPass(String newPass) { this.newPass = newPass;}

    public String getPhone() { return phone;}

    public void setPhone(String phone) { this.phone = phone;}

    public String getSmsCode() { return smsCode;}

    public void setSmsCode(String smsCode) { this.smsCode = smsCode;}
}
