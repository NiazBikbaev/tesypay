package com.splat_spb.validation.annotation;

import com.splat_spb.validation.CurrencyConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CurrencyConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyIso4217 {
    String message() default "Not compliant with the ISO4217";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
