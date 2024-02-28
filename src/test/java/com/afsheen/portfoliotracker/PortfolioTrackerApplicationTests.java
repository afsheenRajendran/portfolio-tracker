package com.afsheen.portfoliotracker;

import com.afsheen.portfoliotracker.service.TxnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PortfolioTrackerApplicationTests extends BaseIT {

	@Autowired
	private TxnService txnService;

	@Test
	void contextLoads() {
		assertNotNull(txnService);
	}

}
