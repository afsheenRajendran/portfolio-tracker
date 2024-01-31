package com.afsheen.portfoliotracker.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Txn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txnId;

    private TxnType type;

    private BigDecimal amount;

    private String description;

    // for JPA only, not used
    public Txn() {
    }

    public Long getTxnId() {
        return txnId;
    }

    public TxnType getType() {
        return type;
    }

    public void setType(TxnType type) {
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
