package com.ycc.common.hint;

public enum UserAdminStatus {
    NORMAL(1),
    NO_EXIST(0);
    private Integer status;
    UserAdminStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
