package com.splat_spb.validation;

import com.splat_spb.validation.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Default comment.
 **/
public class EnumValueConstraintValidator implements ConstraintValidator<EnumValue, Enum> {

    private Class<? extends Enum> enumClass;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        if (enumClass.isEnum()) {
            return Arrays.asList(enumClass.getEnumConstants()).contains(value);
        }
        return false;
    }
}
