package com.acfun.core.enums;

/**
 * 枚举类
 * @author: fqlee
 * @date: created in 15:17 2017/12/20
 */
public enum YesNoEnum {

    YES(1,"是"),
    NO(0,"否");


    private final int value;
    private final String description;

    YesNoEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}
