package com.akosoravecz.accountmanager.controller.request;

import com.akosoravecz.accountmanager.validations.annotations.InvoiceAndDueDateRelation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@InvoiceAndDueDateRelation
public class InvoiceRequest {
    @NotBlank(message = "Customer name cannot be empty!")
    private String customerName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Invoice date cannot be null!")
    private LocalDate invoiceDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Due date cannot be null!")
    private LocalDate dueDate;
    @NotBlank(message = "Item name cannot be empty!")
    private String itemName;
    @NotBlank(message = "Description cannot be empty!")
    private String description;
    @NotNull(message = "Price cannot be null!")
    @Positive(message = "Price cannot be negative")
    private Double price;
}
