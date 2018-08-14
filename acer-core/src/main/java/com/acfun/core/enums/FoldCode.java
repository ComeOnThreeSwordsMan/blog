package com.acfun.core.enums;

public enum FoldCode {

    QUESTION("1","提问"),
    SHARE("2","分享"),
    DISCUSS("3","讨论"),
    SUGGEST("4","建议"),
    ANNOUNCEMENT("5","公告"),
    DYNAMIC("6","动态"),
    ;


    private final String value;
    private final String description;

    FoldCode(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

}
