package com.akosoravecz.accountmanager.model.invoice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private LocalDate invoiceDate;
    @Column(nullable = false)
    private LocalDate dueDate;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
}
