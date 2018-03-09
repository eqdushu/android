package com.tmacbo.eqdushu.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 全局工具类
 *
 * @author liukaifu
 */
public final class Utils {

    private static long lastClickTime;

    /**
     * 防止多次点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取本应用的MD5签名信息
     */
    public static String getSignInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];

            if (sign == null) {
                return null;
            }
            return Md5.MD5(sign.toByteArray()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 直接对对象的某个属性赋值
     *
     * @param obj
     * @param property
     * @param value
     * @throws Exception
     */
    public static void setValue(Object obj, String property, Object value) throws Exception {
        Class<?> c = obj.getClass();
        Field f = c.getDeclaredField(property);
        f.set(obj, value);
    }

    /**
     * 获取一个属性对应的Get函数返回值的类型
     *
     * @param c
     * @param property
     * @return
     */
    public static Class<?> getClassByPropertyName(Class<?> c, String property) {

        try {
            char ch = (char) property.charAt(0);
            String methodName = new StringBuffer(property).delete(0, 1)
                                                          .insert(0, Character.toUpperCase(ch)).insert(0, "get").toString();

            return c.getMethod(methodName, new Class<?>[0]).getReturnType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过Get方法获取某个指定字段的值（String类型）
     *
     * @param obj
     * @param property
     * @return
     */
    public static String getValueStrByGetMethod(Object obj, String property) {
        try {
            Class<?> c = obj.getClass();

            char ch = (char) property.charAt(0);
            String methodName = new StringBuffer(property).delete(0, 1)
                                                          .insert(0, Character.toUpperCase(ch)).insert(0, "get").toString();

            Method method = c.getMethod(methodName);

            Object result = method.invoke(obj);
            if (result == null) {
                return null;
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 字符串转二进制
     *
     * @param str
     * @return
     */
    public static byte[] hex2byte(String str) {
        if (str == null)
            return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串转换成Bitmap类型
     *
     * @param string
     * @return
     */
    public static Bitmap string2Bitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 通过Object的set方法设置属性值
     *
     * @param obj
     * @param property
     * @param value
     */

    public static void setValueBySetMehtod(Object obj, String property, Object value) {
        try {
            Class<?> c = obj.getClass();

            char ch = (char) property.charAt(0);
            String methodName = new StringBuffer(property).delete(0, 1)
                                                          .insert(0, Character.toUpperCase(ch)).insert(0, "set").toString();

            Method method = c.getMethod(methodName, getClassByPropertyName(c, property));

            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Get方法获取请求参数列表
     *
     * @param obj
     * @return
     */
    public static String getReqStrFromGetMethods(Object obj) {
        try {
            Class<?> c = obj.getClass();

            Method[] ms = c.getMethods();
            if (ms == null || ms.length == 0) {
                return null;
            }
            boolean isFirst = true;
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 0; i < ms.length; i++) {
                if (ms[i].getName().startsWith("get") && !ms[i].getName().equals("getClass")) {
                    String res = getValFromObj(ms[i], obj);
                    if (res != null) {
                        if (isFirst) {
                            isFirst = false;
                        } else {
                            stringBuffer.append("&");
                        }
                        stringBuffer.append(getPropertyNameFromGetMethod(ms[i].getName()));
                        stringBuffer.append("=");
                        stringBuffer.append(res);
                    }
                }
            }
            return stringBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取特定的所有Get函数返回值的描述串
     *
     * @param method
     * @param obj
     * @return
     */
    public static String getValFromObj(Method method, Object obj) {
        try {
            Object result = method.invoke(obj);

            if (result == null) {
                return null;
            }
            if (result instanceof List<?>) {
                List<?> c = (List<?>) result;
                if (c.size() == 0) {
                    return "|";
                }
                StringBuffer stringBuffer = new StringBuffer();
                // boolean isFirst = true;
                for (int i = 0; i < c.size(); i++) {
                    Object o = c.get(i);
                    if (o != null) {
                        // if (isFirst) {
                        // isFirst = false;
                        // }
                        // else {
                        // stringBuffer.append("|");
                        // }
                        stringBuffer.append(o.toString());
                        stringBuffer.append("|");
                    }
                }
                return stringBuffer.toString();
            } else {
                return result.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定属性对应的标准Get方法
     *
     * @param id
     * @return
     */
    public static String getPropertyNameFromGetMethod(String id) {
        String str = id.substring(3, id.length());
        char ch = str.charAt(0);
        return new StringBuffer(str).delete(0, 1).insert(0, Character.toLowerCase(ch)).toString();
    }

    // public static void writeByteArrayToFile(byte[] bytes, File file)
    // {
    // if (!file.exists()){
    // file.createNewFile();
    // }
    // FileOutputStream fileOutputStream = new FileOutputStream(file);
    // fileOutputStream.write(bytes);
    // }

    /**
     * 从一个文件中读取内容，转化成byte[],注意必须是小文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static byte[] readByteArrayFromFile(File file) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = fileInputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        byteArrayOutputStream.flush();
        fileInputStream.close();

        byte[] result = byteArrayOutputStream.toByteArray();

        byteArrayOutputStream.close();

        return result;
    }

    /**
     * 从一个输入流读出byte[],以输入流有EOF标志为准
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readByteArrayFromStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        byteArrayOutputStream.flush();
        inputStream.close();

        byte[] result = byteArrayOutputStream.toByteArray();

        byteArrayOutputStream.close();

        return result;
    }

    /**
     * 判断一个字符串是不是空串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.equals(" ");
    }

    /**
     * 判断一个金额
     *
     * @param str
     * @return
     */
    public static boolean isEmpty2Zero(String str) {
        return str == null || str.equals("") || str.equals("0") || str.equals("0.00");
    }

    /**
     * 隐藏手机号中间部分
     *
     * @param str
     * @return
     */
    public static String ChangeMobileNum(String str) {
        String mobileNum = null;
        if (str != null && str.length() == 11) {
            String num = str.substring(0, 3);
            String num1 = str.substring(7, 11);
            mobileNum = num + "****" + num1;
        }
        return mobileNum;
    }

    /**
     * 获取银行卡后四位信息
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String getLastFourNum(String bankCard) {
        if (TextUtils.isEmpty(bankCard) || bankCard.length() < 4) {
            return "";
        }
        return bankCard.substring(bankCard.length() - 4, bankCard.length());
    }

    /**
     * 隐藏身份证号中间部分
     *
     * @param str
     * @return
     */
    public static String ChangeUserNum(String str) {
        String userNum = null;
        if (str != null && (15 == str.length() || str.length() == 18)) {
            if (str.length() == 15) {
                String num = str.substring(0, 3);
                String num1 = str.substring(11, 15);
                userNum = num + "********" + num1;
            } else if (str.length() == 18) {
                String num = str.substring(0, 3);
                String num1 = str.substring(14, 18);
                userNum = num + "***********" + num1;
            }

        }
        return userNum;
    }

    public static Date StringToDate2(String strTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Long time = Long.parseLong(strTime);
        String d = format.format(time);

        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int getAge(String time) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if (Utils.StringToDate2(time) != null) {
            int age = year - Utils.StringToDate2(time).getYear() - 1900;
            return age;
        } else {
            return 0;
        }
    }

    /**
     * 隐藏身份证号中间部分
     *
     * @param str
     * @return
     */
    public static String ChangeBankNo(String str) {
        String bankNo = null;
        if (str != null && (str.length() > 10)) {
            String num = str.substring(0, 3);
            String num1 = str.substring(str.length() - 3, str.length());
            bankNo = num + "***********" + num1;

        }
        return bankNo;
    }

    /**
     * 隐藏姓名
     *
     * @param str
     * @return
     */
    public static String ChangeUserName(String str) {
        String userNum = null;
        if (str != null) {
            String num = str.substring(0, 1);
            for (int i = 0; i < str.length() - 1; i++) {
                num = num + "*";
            }
            userNum = num;
        }
        return userNum;
    }

    /**
     * 从string种提取数字
     *
     * @param text
     * @return
     */
    public static String getNumber(String text) {
        return text.replaceAll("[^0-9]", "");
    }

    /**
     * 字符串转日期
     *
     * @param strDate
     * @return
     */
    public static Date StringToDate(String strDate) {
        //去掉服务器可能传来的错误数据（带有分隔符什么的）
        strDate = getNumber(strDate);
        //yyyyMMddHHmmss格式
        if (strDate.length() == 14) {
            SimpleDateFormat dateFormat_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                return dateFormat_yyyyMMddHHmmss.parse(strDate);
            } catch (Exception e) {
                LogUtils.debug(e.getMessage());
            }
        }
        //yyyyMMdd
        else if (strDate.length() == 8) {
            SimpleDateFormat dateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
            try {
                return dateFormat_yyyyMMdd.parse(strDate);
            } catch (Exception e) {
                LogUtils.debug(e.getMessage());
            }
        } else {
            LogUtils.debug("日期格式错误：" + strDate);
        }
        return null;
    }

    /**
     * 日期格式转化
     *
     * @param date          日期字符串
     * @param dateFormatStr 需要转化的格式
     * @return 日期字符串
     * <p/>
     * 日期字符串 只支持 yyyyMMddHHmmss 和 yyyyMMddHH 两种格式
     */
    public static String changeDate(String date, String dateFormatStr) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return changeDate(StringToDate(date), dateFormatStr);
    }

    public static String changeDate(Date date, String dateFormatStr) {
        if (date == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    public static String changeDate(long date, String dateFormatStr) {
//        if (date >0) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    /**
     * 是否开启状态栏黑色模式
     *
     * @param on true开启 false关闭
     */
    @TargetApi(19)
    public static boolean setStatusBarDarkMode(Window window, boolean on) {
        boolean result = false;
        //调用魅族的适配
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (on) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
            //调用MIUI6的适配
            if (!result) {
                Class<? extends Window> clazz = window.getClass();
                try {
                    int darkModeFlag = 0;
                    Class<?>
                        layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                    Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                    darkModeFlag = field.getInt(layoutParams);
                    Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                    extraFlagField.invoke(window, on ? darkModeFlag : 0, darkModeFlag);
                    result = true;
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

    /**
     * String类型转boolean类型
     *
     * @param value
     * @return
     */
    public static boolean StringToBoolean(String value) {
        if (TextUtils.isEmpty(value))
            return false;
        else {
            if ("1".equals(value)
                    || "TRUE".equals(value.toUpperCase())
                    || "Y".equals(value.toUpperCase())
                    ) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * String 转 number
     *
     * @param value
     * @return
     */
    public static Number StringToNumber(String value) {
        return StringToNumber(value, 0);
    }

    /**
     * String 转 number
     *
     * @param value
     * @return
     */
    public static Number StringToNumber(String value, double defValue) {
        try {
            return Double.valueOf(value);
        } catch (Exception e) {
            return defValue;
        }
    }
}
