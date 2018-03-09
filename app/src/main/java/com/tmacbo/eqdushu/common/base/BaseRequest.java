package com.tmacbo.eqdushu.common.base;

import com.tmacbo.eqdushu.common.CommonConfig;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author tmacbo
 * @Since on 2017/10/7  00:48
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BaseRequest {
    private static final long serialVersionUID = -5614734294693386826L;
    private String appVersion = CommonConfig.getVERSION();
    private String osVersion = android.os.Build.VERSION.RELEASE;
    private String termId = CommonConfig.getIMEI();

    private String requestTm;
    private String deviceId = CommonConfig.PUSH_ID;
    private String channelId = CommonConfig.getCHANNEL();
    private String clientId = CommonConfig.getCLIENT_ID();
    private String termTyp = CommonConfig.PLAT;
    private String type = CommonConfig.USER_TYPE;

    public String getType() {
        return type;
    }

    public BaseRequest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        requestTm = format.format(new Date(System.currentTimeMillis()));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getRequestTm() {
        return requestTm;
    }

    public void setRequestTm(String requestTm) {
        this.requestTm = requestTm;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTermTyp() {
        return termTyp;
    }

    public void setTermTyp(String termTyp) {
        this.termTyp = termTyp;
    }
}
