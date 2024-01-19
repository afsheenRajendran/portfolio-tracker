package com.afsheen.portfoliotracker.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Txn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;
    
    private String type;

    private BigDecimal amount;

    private String description;

    // for JPA only, not used
    public Txn() {
    }

    public Long getTxnId() {
        return txnId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
