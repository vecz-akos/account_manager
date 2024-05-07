package com.akosoravecz.accountmanager.controller.api;

import com.akosoravecz.accountmanager.controller.request.InvoiceRequest;
import com.akosoravecz.accountmanager.dto.model.invoice.InvoiceDto;
import com.akosoravecz.accountmanager.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/invoice")
@RequiredArgsConstructor
@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/")
    public InvoiceDto addInvoice(@RequestBody @Valid InvoiceRequest invoiceRequest) {
        return invoiceService.addInvoice(invoiceRequest);
    }

    @GetMapping("/")
    public Collection<InvoiceDto> getAllInvoice() {
        return invoiceService.getAllInvoice();
    }

    @GetMapping("/{id}")
    public InvoiceDto getInvoice(@PathVariable(value="id") Long id) {
        return invoiceService.getInvoice(id);
    }
}
