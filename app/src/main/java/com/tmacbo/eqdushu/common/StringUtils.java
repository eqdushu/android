package com.tmacbo.eqdushu.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author tmacbo
 * @Since on 2017/11/16  15:26
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public final class StringUtils {
    /**
     * 验证用户名只包含字母，数字，中文
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";//正则表达式
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String stringTirm(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("[", "").replace("]", "").replace("\"", "");
    }
}
