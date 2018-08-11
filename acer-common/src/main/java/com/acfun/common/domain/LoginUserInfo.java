package com.acfun.common.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 登录用户信息
 * @author kwer
 * @Date 2017/12/15 10:25
 */
@Data
@ToString
public class LoginUserInfo {
    private String userId;
    private String orgId;
}
