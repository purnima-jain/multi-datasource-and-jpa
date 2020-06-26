package com.purnima.jain.multidatasource.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.purnima.jain.multidatasource.jpa.mysql.entity.CustomerMySqlEntity;
import com.purnima.jain.multidatasource.jpa.mysql.repo.MySqlCustomerRepository;
import com.purnima.jain.multidatasource.jpa.postgres.entity.CustomerPostgresEntity;
import com.purnima.jain.multidatasource.jpa.postgres.repo.PostgresCustomerRepository;

@Configuration
public class MultiDatasourceAndJpaApplicationRunner implements ApplicationRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MultiDatasourceAndJpaApplicationRunner.class);
	
	@Autowired
	private MySqlCustomerRepository mySqlCustomerRepository; 
	
	@Autowired
	private PostgresCustomerRepository postgresCustomerRepository; 

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Integer customerId = 1;
		
		CustomerMySqlEntity customerMySqlEntity = mySqlCustomerRepository.findById(customerId).orElse(null);
		logger.info("CustomerMySqlEntity :: {}", customerMySqlEntity);
		
		CustomerPostgresEntity customerPostgresEntity = postgresCustomerRepository.findById(customerId).orElse(null);
		logger.info("CustomerPostgresEntity :: {}", customerPostgresEntity);
		
	}

}
