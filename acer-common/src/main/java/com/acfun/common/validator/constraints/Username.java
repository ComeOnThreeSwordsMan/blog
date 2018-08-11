/*
 * Copyright (c) 2017 www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.common.validator.constraints;

import com.acfun.common.validator.constraintvalidators.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用户名验证注解
 *
 * @author hsky www.servingcloud.com
 * @since 2017年4月25日 下午11:50:22
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UsernameValidator.class)
public @interface Username {

    /**
     * 默认错误消息
     *
     * @return
     */
    String message() default "请输入有效的用户名";

    /**
     * 分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 负载
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 指定多个时使用
     */
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Username[] value();
    }
}
