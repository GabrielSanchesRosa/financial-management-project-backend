package com.sanches.financial_management_project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userID;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "transaction_type", nullable = false, length = 10)
    private String transactionType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "receipt_url", length = 500)
    private String receiptURL;

    @Column(name = "create_at", nullable = false)
    private String createAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;
}

