package com.afsheen.portfoliotracker.entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TxnType {
    INWARD("IW"),

    OUTWARD("OW");

    TxnType(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return this.code;
    }

    public static TxnType of(String input) {
        return Stream.of(TxnType.values())
                .filter(txnType -> (txnType.name().equals(input) || (txnType.getCode().equals(input))))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
