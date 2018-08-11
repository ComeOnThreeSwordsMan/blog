package com.acfun.common.constant;

/**
 * 系统状态枚举对象。
 * @author kwer
 * 2017/7/27/027 17:23
 */
public enum SysStatus {
    ENABLED(1, "已启用"),
    DISABLED(2, "已禁用"),
    DELETED(3, "删除");
////    LOCKED(1,"已锁定"),
////    NOT_LOCK(2,"未锁定");

    private final int value;
    private final String description;

    SysStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static SysStatus valueOf(int code) {
        for (SysStatus userStatus : values()) {
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
