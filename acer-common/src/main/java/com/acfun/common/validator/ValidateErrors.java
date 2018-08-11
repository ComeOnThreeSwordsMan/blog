package com.acfun.common.validator;

import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsky www.servingcloud.com
 * @since 2017年6月4日 下午5:41:51
 */
@Data
public class ValidateErrors implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局错误
     */
    private List<String> globalErrors = new ArrayList<>();

    /**
     * 字段错误
     */
    private Map<String, String> fieldErrors = new HashMap<String, String>();

    public ValidateErrors(Errors errors) {
        if (errors.hasGlobalErrors()) {
            for (ObjectError error : errors.getGlobalErrors()) {
                String message = error.getDefaultMessage();
                this.globalErrors.add(message);
            }
        }
        if (errors.hasFieldErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                String message = error.getDefaultMessage();
                this.fieldErrors.put(error.getField(), message);
            }
        }
    }

}
