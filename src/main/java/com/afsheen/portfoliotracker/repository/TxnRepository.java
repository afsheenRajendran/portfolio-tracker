package com.afsheen.portfoliotracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afsheen.portfoliotracker.entity.Txn;

public interface TxnRepository extends JpaRepository<Txn, Long>{
    List<Txn> findByType(String type);
}
