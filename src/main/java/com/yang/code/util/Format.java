package com.yang.code.util;

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
}
