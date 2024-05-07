package com.akosoravecz.accountmanager.repository.invoice;

import com.akosoravecz.accountmanager.model.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
