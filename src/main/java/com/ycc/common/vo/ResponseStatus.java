package com.ycc.common.vo;

import lombok.Getter;
import lombok.Setter;

public enum ResponseStatus {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");


    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    public String getMessage(Integer code) {
        ResponseStatus[] status = ResponseStatus.values();
        for (ResponseStatus r : status) {
            if (r.code.equals(code)) {
                return r.message;
            }
        }
        return null;
    }
}
