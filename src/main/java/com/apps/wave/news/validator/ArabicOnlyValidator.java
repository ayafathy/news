package com.apps.wave.news.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Validator implementation
public class ArabicOnlyValidator implements ConstraintValidator<ArabicOnly, String> {
 private static final String ARABIC_REGEX = "^[\\u0600-\\u06FF\\u0750-\\u077F\\u08A0-\\u08FF\\uFB50-\\uFDFF\\uFE70-\\uFEFF]+$";

 @Override
 public boolean isValid(String value, ConstraintValidatorContext context) {
     if (value == null || value.isEmpty()) {
         return true;  // Consider null or empty input valid, depends on requirements
     }
     return value.matches(ARABIC_REGEX);
 }
}