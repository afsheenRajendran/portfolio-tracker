package com.afsheen.portfoliotracker.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxnRequestBody {
    private String type;

    private BigDecimal amount;

    private String description;
}