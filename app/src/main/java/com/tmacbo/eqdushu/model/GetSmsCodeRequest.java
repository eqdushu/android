package com.tmacbo.eqdushu.model;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/24  16:01
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class GetSmsCodeRequest extends BaseRequest {
    private String mblNo;
    private String smsTyp;

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    public String getSmsTyp() {
        return smsTyp;
    }

    public void setSmsTyp(String smsTyp) {
        this.smsTyp = smsTyp;
    }
}
