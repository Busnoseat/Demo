package com.busnoseat.demo.web.converter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * The type ValidationUtil.
 *
 * @author xubo
 * @Description:parameters validation  used in lots of params
 * @Date 2016/9/13
 */
public class ValidationUtil {
    public static <T> Set<ConstraintViolation<T>> validEntity(T obj) {
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(obj);
        return set;

    }
}
