package com.purnima.jain.multidatasource.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiDatasourceAndJpaApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MultiDatasourceAndJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourceAndJpaApplication.class, args);
		logger.info("MultiDatasourceAndJpaApplication Started........");
	}

}
