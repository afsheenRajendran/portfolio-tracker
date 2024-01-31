package com.afsheen.portfoliotracker.repository;

import java.util.List;

import com.afsheen.portfoliotracker.entity.TxnType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.afsheen.portfoliotracker.entity.Txn;

public interface TxnRepository extends JpaRepository<Txn, Long>{
    List<Txn> findByType(TxnType type);
}
