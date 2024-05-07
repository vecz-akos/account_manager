package com.akosoravecz.accountmanager.validations.annotations;

import com.akosoravecz.accountmanager.validations.InvoiceAndDueDateRelationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvoiceAndDueDateRelationValidator.class)
public @interface InvoiceAndDueDateRelation {
    String message() default "Invoice date must be before due date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
