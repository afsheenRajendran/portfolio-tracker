package com.afsheen.portfoliotracker.entity;

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

    public static TxnType of(String code) {
        return Stream.of(TxnType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
