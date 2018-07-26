package com.yang.code.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2018/7/26.
 * 格式转换工具类
 */
public class Format {

    public static int getInt(Object obj) {
        int def = 0;
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    public static int getInt(Object obj, int def) {
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    public static long getLong(Object obj) {
        long def = 0;
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    public static long getLong(Object obj, long def) {
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    public static float getFloat(Object obj) {
        float def = 0.0f;
        if (obj != null) {
            try {
                def = Float.parseFloat(obj == null ? "0" : obj.toString());
            } catch (Exception e) {
            }
        }
        return def;
    }

    public static float getFloat(Object obj, float def) {
        if (obj != null) {
            try {
                def = Float.parseFloat(obj == null ? "0" : obj.toString());
            } catch (Exception e) {
            }
        }
        return def;
    }

    public static double getDouble(Object obj) {
        double def = 0.0;
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    public static double getDouble(Object obj, double def) {
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }
        }
        return def;
    }

    /**
     * 左侧填充
     *
     * @param oriStr
     * @param len
     * @param alexin
     * @return
     */
    public static String padLeft(String oriStr, int len, char alexin) {
        return pad(oriStr, len, alexin) + oriStr;
    }

    /**
     * 右侧填充
     *
     * @param oriStr
     * @param len
     * @param alexin
     * @return
     */
    public static String padRight(String oriStr, int len, char alexin) {
        return oriStr + pad(oriStr, len, alexin);
    }

    private static String pad(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        return str;
    }

    /**
     * long转日期格式
     *
     * @param mins     时间戳
     * @param formater 日期格式
     */
    public static String GetFormatDate(long mins, String formater) {
        if (StringUtils.isEmpty(formater)) {
            formater = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(formater);
        Date date = new Date(mins);
        return format.format(date);
    }
}
