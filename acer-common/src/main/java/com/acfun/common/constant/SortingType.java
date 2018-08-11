package com.acfun.common.constant;

/**
 * 排序方式枚举对象。
 *
 * @author winston
 *         2017/7/27/027 16:35
 */
public enum SortingType {
    DESCEND("descend"),
    ASCEND("ascend"),
    DESC("desc"),
    ASC("asc"),
    ;

    private final String value;

    SortingType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
