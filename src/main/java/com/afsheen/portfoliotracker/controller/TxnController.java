package com.afsheen.portfoliotracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.afsheen.portfoliotracker.entity.Txn;
import com.afsheen.portfoliotracker.service.TxnService;

@RestController
@RequestMapping("/txns")
public class TxnController {
    
    @Autowired
    private TxnService txnService;

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Txn create(@RequestBody Txn txn) {
        return txnService.save(txn);
    }

    @GetMapping
    public List<Txn> findAll() {
        return txnService.findAll();
    }


}
