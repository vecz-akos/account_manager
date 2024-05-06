package com.akosoravecz.accountmanager.validations.annotations;

import com.akosoravecz.accountmanager.validations.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "Username is not available.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
