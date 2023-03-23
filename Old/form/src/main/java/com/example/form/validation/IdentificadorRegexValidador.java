package com.example.form.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]")){
            return true;
        }
        return false;
    }

}
