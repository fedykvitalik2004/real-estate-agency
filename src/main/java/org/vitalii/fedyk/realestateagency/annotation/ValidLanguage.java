package org.vitalii.fedyk.realestateagency.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.vitalii.fedyk.realestateagency.annotation.validator.LanguageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LanguageValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLanguage {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
