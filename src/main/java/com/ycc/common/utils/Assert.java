package com.ycc.common.utils;

import com.ycc.common.exception.ApiException;

/**
 * 断言工具类
 * @author ccc
 **/
public class Assert {
    public Assert() {
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ApiException(message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object != null) {
            throw new ApiException(message);
        }
    }
    /**
     * 不等于0的时候，抛出异常
     **/
    public static void isNotZero(Integer obj,String message){
        if (obj > 0) {
            throw new ApiException(message);
        }
    }
}
