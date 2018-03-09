package com.tmacbo.eqdushu.model.login;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/6
 * Time: 11:31
 * FIXME
 */
public class LoginData {
    /**
     * userType : reader
     * userId : 4
     * uuid : 0d453c2003fb4ac5a49d7616d8f07a26_1504005271029
     * xAuthToken : adWhHtNaORA=_ddaf86a83ea5406e9c949cad88a70db2
     * phone : 15512345678
     * deviceId : null
     */

    private String userType;
    private long userId;
    private String uuid;
    private String xAuthToken;
    private String phone;
    private String deviceId;
    private String company;
    private String companyId;

    public String getUserType() { return userType;}

    public void setUserType(String userType) { this.userType = userType;}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUuid() { return uuid;}

    public void setUuid(String uuid) { this.uuid = uuid;}

    public String getXAuthToken() { return xAuthToken;}

    public void setXAuthToken(String xAuthToken) { this.xAuthToken = xAuthToken;}

    public String getPhone() { return phone;}

    public void setPhone(String phone) { this.phone = phone;}

    public String getxAuthToken() {
        return xAuthToken;
    }

    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
