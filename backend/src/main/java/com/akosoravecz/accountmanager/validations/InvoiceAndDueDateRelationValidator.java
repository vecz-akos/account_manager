package com.akosoravecz.accountmanager.validations;

import com.akosoravecz.accountmanager.controller.request.InvoiceRequest;
import com.akosoravecz.accountmanager.validations.annotations.InvoiceAndDueDateRelation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceAndDueDateRelationValidator implements ConstraintValidator<InvoiceAndDueDateRelation, InvoiceRequest> {

    public void initialize(InvoiceAndDueDateRelation invoiceAndDueDateRelation) {}

    @Override
    public boolean isValid(InvoiceRequest invoiceRequest, ConstraintValidatorContext constraintValidatorContext) {
        return invoiceRequest.getInvoiceDate().isBefore(invoiceRequest.getDueDate());
    }
}
