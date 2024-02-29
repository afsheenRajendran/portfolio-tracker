package com.afsheen.portfoliotracker.mapper;

import com.afsheen.portfoliotracker.dto.TxnRequestBody;
import com.afsheen.portfoliotracker.entity.TxnEntity;
import com.afsheen.portfoliotracker.entity.TxnType;
import org.springframework.stereotype.Component;

@Component
public class TxnMapper {

    public TxnEntity buildTxnEntity(TxnRequestBody txnRequestBody) {
        TxnEntity txnEntity = new TxnEntity();

        txnEntity.setType(TxnType.of(txnRequestBody.getType()));
        txnEntity.setAmount(txnRequestBody.getAmount());
        txnEntity.setDescription(txnRequestBody.getDescription());

        return txnEntity;
    }
}
