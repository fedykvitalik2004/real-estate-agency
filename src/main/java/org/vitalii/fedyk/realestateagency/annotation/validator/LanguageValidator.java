package org.vitalii.fedyk.realestateagency.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vitalii.fedyk.realestateagency.annotation.ValidLanguage;
import org.vitalii.fedyk.realestateagency.config.AvailableLanguages;

import java.util.Arrays;
import java.util.Locale;

@Component
@AllArgsConstructor
public class LanguageValidator implements ConstraintValidator<ValidLanguage, Locale> {
    private AvailableLanguages availableLanguages;

    @Override
    public void initialize(final ValidLanguage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final Locale value, final ConstraintValidatorContext context) {
        System.out.println(availableLanguages.getDefaultLanguage());
        return Arrays.stream(availableLanguages.getAllLanguages()).anyMatch(l -> l.equals(value.getLanguage()));
    }
}