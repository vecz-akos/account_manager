package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.InvoiceRequest;
import com.akosoravecz.accountmanager.dto.model.invoice.InvoiceDto;
import com.akosoravecz.accountmanager.repository.invoice.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface InvoiceService {
    InvoiceDto addInvoice(InvoiceRequest invoiceRequest);
    Collection<InvoiceDto> getAllInvoice();
    InvoiceDto getInvoice(Long id);
}
