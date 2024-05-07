package com.akosoravecz.accountmanager.service;

import com.akosoravecz.accountmanager.controller.request.InvoiceRequest;
import com.akosoravecz.accountmanager.dto.mapper.InvoiceMapper;
import com.akosoravecz.accountmanager.dto.model.invoice.InvoiceDto;
import com.akosoravecz.accountmanager.model.invoice.Invoice;
import com.akosoravecz.accountmanager.repository.invoice.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Override
    public InvoiceDto addInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setItemName(invoiceRequest.getItemName());
        invoice.setDescription(invoiceRequest.getDescription());
        invoice.setPrice(invoiceRequest.getPrice());
        Invoice newInvoice = invoiceRepository.save(invoice);
        return InvoiceMapper.toInoiceDto(newInvoice);
    }

    @Override
    public Collection<InvoiceDto> getAllInvoice() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toInoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return InvoiceMapper.toInoiceDto(invoice);
    }
}
