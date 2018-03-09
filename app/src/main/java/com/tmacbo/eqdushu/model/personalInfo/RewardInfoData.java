package com.tmacbo.eqdushu.model.personalInfo;

import java.io.Serializable;

/**
 * @Author tmacbo
 * @Since on 2018/3/7  20:54
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class RewardInfoData implements Serializable {

    /**
     * id : 1
     * createTime : 2018-02-26 17:03:30
     * updateTime : 2018-03-01 11:21:23
     * userId : 4
     * type : gas
     * address : AeQEMkh8NXZ2qUEgCw5QE8SYbzP2F7GZ5F
     * asset : 0.45
     */

    private int id;
    private String createTime;
    private String updateTime;
    private int userId;
    private String type;
    private String address;
    private double asset;
    private double increase;

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getCreateTime() { return createTime;}

    public void setCreateTime(String createTime) { this.createTime = createTime;}

    public String getUpdateTime() { return updateTime;}

    public void setUpdateTime(String updateTime) { this.updateTime = updateTime;}

    public int getUserId() { return userId;}

    public void setUserId(int userId) { this.userId = userId;}

    public String getType() { return type;}

    public void setType(String type) { this.type = type;}

    public String getAddress() { return address;}

    public void setAddress(String address) { this.address = address;}

    public double getAsset() { return asset;}

    public void setAsset(double asset) { this.asset = asset;}

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }
}
