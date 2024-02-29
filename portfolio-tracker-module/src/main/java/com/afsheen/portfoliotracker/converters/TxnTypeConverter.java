package com.afsheen.portfoliotracker.converters;

import com.afsheen.portfoliotracker.entity.TxnType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TxnTypeConverter implements AttributeConverter<TxnType, String> {
    @Override
    public String convertToDatabaseColumn(TxnType txnType) {
        if (txnType == null) {
            return null;
        }

        return txnType.getCode();
    }

    @Override
    public TxnType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return TxnType.of(dbData);
    }
}