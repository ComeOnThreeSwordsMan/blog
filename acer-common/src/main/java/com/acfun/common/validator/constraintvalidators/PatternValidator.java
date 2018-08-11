/*
 * Copyright (c) 2017 www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.common.validator.constraintvalidators;


import com.acfun.common.validator.constraints.Pattern;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * 自定义正则表达式验证注解验证器
 *
 * @author hsky www.servingcloud.com
 * @since 2017年4月25日 下午11:50:22
 */
public class PatternValidator implements ConstraintValidator<Pattern, String> {

    /**
     * 正则表达式
     */
    private String regexp;

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(Pattern constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || java.util.regex.Pattern.matches(regexp, value);
    }
}
