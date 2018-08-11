package com.acfun.common.constant;

import java.util.HashSet;
import java.util.Set;

/***
 * 缓存的key。
 */
public enum CacheKey {
    // 登录失败记录
//    LOGIN_FAILED("LoginFailed"),
    // 忘记密码的短信验证码记录
//    PASSWORD_FORGOTTEN_VERIFICATION_CODE("PasswordForgottenSmsVerificationCode"),
    // 忘记密码的密码重置凭证记录
//    PASSWORD_RESET_TICKET("PasswordResetTicket"),
    // 用户权限
    USER_PERMISSION("UserPermission"),
    USER_LONGINFO("UserLoginInfo"),
    //用户所属组织
    USER_ORGANIZATION("serOrganization"),
    //数据字典id-name 储存id和name键值对
    DICT_ID_AND_NAME("dictIdAndName"),
    // 用户数据权限
//    USER_DATA_PERMISSION("UserDataPermission")
    LOGIN_RECORD("LoginRecord"),;

    private final String key;

    CacheKey(String key) {
        this.key = key;
    }

    public String value() {
        return this.key;
    }

    @Override
    public String toString() {
        return this.key;
    }

    // 检查是否有相同的key
    static {
        Set<String> s = new HashSet<>();
        String value;
        for (CacheKey cacheKey : CacheKey.values()) {
            value = cacheKey.value();
            if (s.contains(value)) {
                throw new RuntimeException("Duplicated cache cacheKey " + value);
            }
            s.add(value);
        }
    }

    public static String generateOrganizationCacheKey(String userId) {
        return OauthClient.CLIENT_ID.value() + ":" + CacheKey.USER_ORGANIZATION.value() + ":" + userId;
    }

    public static String dictIdAndNameCacheKey() {
        return OauthClient.CLIENT_ID.value() + ":" + CacheKey.DICT_ID_AND_NAME.value();
    }

    public static String loginRecordCacheKey(String userName) {
        return OauthClient.CLIENT_ID.value() + ":" + CacheKey.LOGIN_RECORD.value() + ":" + userName;
    }
}
