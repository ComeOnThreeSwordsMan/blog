package com.acfun.core.enums;

/**
 * 响应码枚举对象。
 *
 * @author winston
 *         2017/7/27/027 16:35
 */
public enum ResponseCode {
    //通用
    OK(0, 0,"OK"),
    REG_SUCCESS(1,1, "注册成功"),
    LOGIN_SUCCESS(2,2, "登陆成功"),
    USER_NAME_EXIST(-201,-201, "用户名已存在"),
    EMAIL_EXIST(-202,-202 ,"邮箱已存在"),
    USER_NAME_NOT_EXIST(-203,-203, "用户不存在"),
    WRONG_OLD_PASSWORD(-204,-204, "密码错误"),
    ;


    private final int status;
    private final int code;
    private final String description;

    ResponseCode(int status,int code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public int status() {
        return this.status;
    }
    public int code() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }



    @Override
    public String toString() {
        return status+"";
    }

}
