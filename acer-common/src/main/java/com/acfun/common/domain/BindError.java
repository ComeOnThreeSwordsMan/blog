package com.acfun.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 校验实体类
 * @author: fqlee
 * @date: created in 17:33 2017/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindError {

    /**
     * 字段
     */
    private String field;

    /**
     * 原始值
     */
    private Object rejectedValue;

    /**
     * 默认错误提示
     */
    private String defaultMessage;

}
