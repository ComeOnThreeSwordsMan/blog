package com.acfun.common.constant;

/**
 * 认证客户端信息。
 *
 * @author winston
 *         2017/7/29/029 9:31
 */
public enum OauthClient {
    CLIENT_ID("vscloud", "客户端ID"),
//    CLIENT_NAME("供应链金融-系统管理平台", "客户端名称"),
//    CLIENT_SECRET("76590e07a0cc297c9cea29938fc275ef", "客户端密钥")
 ;

    private final String value;
    private final String description;

    OauthClient(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static OauthClient getOauthConstantDesc(String code) {
        for (OauthClient oauthConstant : values()) {

            //modifier hsky 字符串不能用等号
            if (oauthConstant.value.equals(code)) {
                return oauthConstant;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }

    @Override
    public String toString() {
        return this.value;
    }
}
