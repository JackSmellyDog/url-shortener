package com.shaposhnikandrii.urlshortener.validator.url;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
public @interface ValidUrl {
  String message() default "Invalid Url!";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
