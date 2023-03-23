package com.example.form.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequeridoValidator implements ConstraintValidator<Requerido, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || !StringUtils.hasText(s)){
            return false;
        }
        return true;
    }
}
