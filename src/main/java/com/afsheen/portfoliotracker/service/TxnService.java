package com.afsheen.portfoliotracker.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afsheen.portfoliotracker.entity.Txn;
import com.afsheen.portfoliotracker.repository.TxnRepository;

@Service
public class TxnService {
    Logger logger = LoggerFactory.getLogger(TxnService.class);

    @Autowired
    private TxnRepository txnRepository;

    public Txn save(Txn txn) {
        logger.info("Calling save with txn desc = " + txn.getDescription());
        return txnRepository.save(txn);
    }

    public List<Txn> findAll() {
        return txnRepository.findAll();
    }

    public List<Txn> findByType(String type) {
        return txnRepository.findByType(type);
    }
}
