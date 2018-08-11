package com.acfun.common.constant;

/**
 * 通用状态枚举对象。
 * @author winston
 * 2017/7/27/027 17:23
 */
public enum CommonStatus {
    DELETED(-100, "删除"),
    STAGE(0, "暂存"),
    DISABLED(-10, "停用"),
    ENABLED(100,"生效");

    private final int value;
    private final String description;

    CommonStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static CommonStatus valueOf(int code) {
        for (CommonStatus userStatus : values()) {
            if (userStatus.value == code) {
                return userStatus;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
