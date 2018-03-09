package com.tmacbo.eqdushu.model.login;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/26  14:38
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class CompanyDataRequest extends BaseRequest {
    /**
     * companyName : sk
     * companyCode : sdjda
     * type : admin
     */
    private String companyId;
    private String companyName;
    private String companyCode;
    private String userType;

    private String userName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() { return companyName;}

    public void setCompanyName(String companyName) { this.companyName = companyName;}

    public String getCompanyCode() { return companyCode;}

    public void setCompanyCode(String companyCode) { this.companyCode = companyCode;}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
