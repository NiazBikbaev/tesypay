package com.splat_spb.validation;

import com.splat_spb.validation.annotation.CurrencyIso4217;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

/**
 * Default comment.
 **/
@Component
public class CurrencyConstraintValidator implements ConstraintValidator<CurrencyIso4217, String> {
    @Override
    public void initialize(CurrencyIso4217 constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Currency.getInstance(value);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
