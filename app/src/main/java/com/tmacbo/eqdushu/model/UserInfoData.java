package com.tmacbo.eqdushu.model;

/**
 * @Author tmacbo
 * @Since on 2017/11/15  11:16
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

class UserInfoData {
    private static final UserInfoData ourInstance = new UserInfoData();

    static UserInfoData getInstance() {
        return ourInstance;
    }

    private UserInfoData() {
    }
}
