package com.afsheen.portfoliotracker.repository;

import com.afsheen.portfoliotracker.BaseIT;
import com.afsheen.portfoliotracker.entity.TxnEntity;
import com.afsheen.portfoliotracker.entity.TxnType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TxnRepositoryDataJpaTest extends BaseIT {

    Logger logger = LoggerFactory.getLogger(TxnRepositoryDataJpaTest.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TxnRepository txnRepository;

    @Test
    void test1() {
        jdbcTemplate.update("INSERT INTO account(account_id, name) values (100, 'acct100');");

        jdbcTemplate.update("INSERT INTO txn(txn_id, account_id, type, amount, description) values (100001, 100, \"IW\", 1000, \"initial setup\");");
        jdbcTemplate.update("INSERT INTO txn(txn_id, account_id, type, amount, description) values (100002, 100, 'OW', 500, 'wdl 1');");
        jdbcTemplate.update("INSERT INTO txn(txn_id, account_id, type, amount, description) values (100003, 100, 'IW', 200, 'top-up');");
   
        List<TxnEntity> results = txnRepository.findByType(TxnType.INWARD);
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void test2() {
        jdbcTemplate.update("INSERT INTO account(account_id, name) values (110, 'acct110');");

        jdbcTemplate.update("INSERT INTO txn(txn_id, account_id, type, amount, description) values (110001, 100, \"IW\", 1000, \"initial setup\");");
        jdbcTemplate.update("INSERT INTO txn(txn_id, account_id, type, amount, description) values (110002, 100, 'OW', 500, 'wdl 1');");

        List<TxnEntity> results = txnRepository.findByType(TxnType.INWARD);
        assertNotNull(results);
        assertEquals(1, results.size());
    }


}
