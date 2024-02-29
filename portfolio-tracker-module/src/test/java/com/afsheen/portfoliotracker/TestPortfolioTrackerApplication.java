package com.afsheen.portfoliotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestPortfolioTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(PortfolioTrackerApplication::main).with(TestPortfolioTrackerApplication.class).run(args);
	}

}
