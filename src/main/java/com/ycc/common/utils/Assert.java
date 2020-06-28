package com.ycc.common.utils;
/**
 * 断言工具类
 **/
public class Assert {
    public Assert() {
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }
}
