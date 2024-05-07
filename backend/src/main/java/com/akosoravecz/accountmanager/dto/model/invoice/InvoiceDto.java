package com.akosoravecz.accountmanager.dto.model.invoice;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InvoiceDto {
    private String customerName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate invoiceDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;
    private String itemName;
    private String description;
    private Double price;
}
