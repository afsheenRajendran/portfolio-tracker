package com.afsheen.portfoliotracker.repository;

import java.util.List;

import com.afsheen.portfoliotracker.entity.TxnType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.afsheen.portfoliotracker.entity.TxnEntity;

public interface TxnRepository extends JpaRepository<TxnEntity, Long>{
    List<TxnEntity> findByType(TxnType type);
}
