package com.afsheen.portfoliotracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afsheen.portfoliotracker.entity.Txn;
import com.afsheen.portfoliotracker.repository.TxnRepository;

@Service
public class TxnService {
    
    @Autowired
    private TxnRepository txnRepository;

    public Txn save(Txn txn) {
        return txnRepository.save(txn);
    }

    public List<Txn> findAll() {
        return txnRepository.findAll();
    }

    public List<Txn> findByType(String type) {
        return txnRepository.findByType(type);
    }
}
