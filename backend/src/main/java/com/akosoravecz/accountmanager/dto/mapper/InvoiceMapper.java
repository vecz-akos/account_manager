package com.akosoravecz.accountmanager.dto.mapper;

import com.akosoravecz.accountmanager.dto.model.invoice.InvoiceDto;
import com.akosoravecz.accountmanager.model.invoice.Invoice;

public class InvoiceMapper {
    public static InvoiceDto toInoiceDto(Invoice invoice) {
        return InvoiceDto.builder()
                .customerName(invoice.getCustomerName())
                .invoiceDate(invoice.getInvoiceDate())
                .dueDate(invoice.getDueDate())
                .itemName(invoice.getItemName())
                .description(invoice.getDescription())
                .price(invoice.getPrice())
                .build();
    }
}
