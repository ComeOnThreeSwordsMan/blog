/*
 * Copyright (c) 2017 www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.common.validator.constraintvalidators;

import com.acfun.common.validator.ValidateUtils;
import com.acfun.common.validator.constraints.Code;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * 编号验证注解验证器
 *
 * @author hsky www.servingcloud.com
 * @since 2017年4月25日 下午11:50:22
 */
public class CodeValidator implements ConstraintValidator<Code, String> {

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(Code constraintAnnotation) {
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || ValidateUtils.validateCode(value);
    }
}
