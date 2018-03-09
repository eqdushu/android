package com.tmacbo.eqdushu.utils;

/**
 * @author Quan
 * @version 1.0
 * @date 2015-04-29 10:17
 * @since 公共常量定义
 *        CALL_XXX 表示呼叫，用于通知UI操作
 *        WHAT_xxx 表示发生了事件，内部逻辑处理
 * --------------------------------------------------------------------
 */
public class CommonConstants {
    /**
     * 静态常量初始值
     * 所有的程序有需要的静态常量用
     * CommonConstants.FIRST_VAL++
     * 来获取
     */
    public static int FIRST_VAL = 13;
    /** 呼叫函数（UI） */
    public static final int CALL_FUNCTION = FIRST_VAL++;
    /** 更新图片 */
    public static final int WHAT_UPDATE_IMG = FIRST_VAL++;
    /** 显示提示 */
    public static final int WHAT_SHOW_TOAST_TEXT = FIRST_VAL++;
    /** 呼叫函数 */
    public static final int WHAT_CALL_FUNCTION = FIRST_VAL++;

}
