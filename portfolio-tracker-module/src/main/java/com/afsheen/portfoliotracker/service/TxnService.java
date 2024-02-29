package com.afsheen.portfoliotracker.service;

import java.util.List;

import com.afsheen.portfoliotracker.entity.TxnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afsheen.portfoliotracker.entity.TxnEntity;
import com.afsheen.portfoliotracker.repository.TxnRepository;

@Service
public class TxnService {
    Logger logger = LoggerFactory.getLogger(TxnService.class);

    @Autowired
    private TxnRepository txnRepository;

    public TxnEntity save(TxnEntity txnEntity) {
        logger.info("Calling save with txn desc = " + txnEntity.getDescription());
        return txnRepository.save(txnEntity);
    }

    public List<TxnEntity> findAll() {
        return txnRepository.findAll();
    }

    public List<TxnEntity> findByType(String rawTxnType) {
        TxnType txnType = TxnType.of(rawTxnType);

        return txnRepository.findByType(txnType);
    }
}
