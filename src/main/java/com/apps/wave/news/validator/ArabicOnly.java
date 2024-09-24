package com.apps.wave.news.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ArabicOnlyValidator.class)
public @interface ArabicOnly {
    String message() default "Input must contain Arabic characters only";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}