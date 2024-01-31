package com.afsheen.portfoliotracker.mapper;

import com.afsheen.portfoliotracker.dto.TxnRequestBody;
import com.afsheen.portfoliotracker.entity.Txn;
import com.afsheen.portfoliotracker.entity.TxnType;
import org.springframework.stereotype.Component;

@Component
public class TxnMapper {

    public Txn buildTxn(TxnRequestBody txnRequestBody) {
        Txn txn = new Txn();

        txn.setType(TxnType.of(txnRequestBody.getType()));
        txn.setAmount(txnRequestBody.getAmount());
        txn.setDescription(txnRequestBody.getDescription());

        return txn;
    }
}
