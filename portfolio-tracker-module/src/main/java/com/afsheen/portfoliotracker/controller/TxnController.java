package com.afsheen.portfoliotracker.controller;

import java.util.List;

import com.afsheen.portfoliotracker.dto.TxnRequestBody;
import com.afsheen.portfoliotracker.mapper.TxnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.afsheen.portfoliotracker.entity.TxnEntity;
import com.afsheen.portfoliotracker.service.TxnService;

@RestController
@RequestMapping("/txns")
public class TxnController {
    
    @Autowired
    private TxnService txnService;

    @Autowired
    private TxnMapper txnMapper;

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public TxnEntity create(@RequestBody TxnRequestBody txnRequestBody) {
        TxnEntity txnEntity = txnMapper.buildTxnEntity(txnRequestBody);

        return txnService.save(txnEntity);
    }

    @GetMapping
    public List<TxnEntity> findAllTxns() {
        return txnService.findAll();
    }


}
