package com.tmacbo.eqdushu.common.base;

/**
 * Created by jack on 2017/6/20
 */

public interface BaseRequestContract<T> {

    void onRequestSuccessData(T data);

    void onRequestFaliure(String rspInf);

}