package com.purnima.jain.multidatasource.jpa.mysql.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "mySqlEntityManagerFactory", 
		transactionManagerRef = "mySqlTransactionManager", 
		basePackages = { "com.purnima.jain.multidatasource.jpa.mysql" })
public class MySqlConfig {

	@Primary // Arbitrarily making this as Primary
	@Bean(name = "mySqlDataSource")
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary // Arbitrarily making this as Primary
	@Bean(name = "mySqlEntityManagerFactory")	
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mySqlDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("com.purnima.jain.multidatasource.jpa.mysql.entity")
				.build();
	}

	@Primary // Arbitrarily making this as Primary
	@Bean(name = "mySqlTransactionManager")
	public PlatformTransactionManager mySqlTransactionManager(@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory mySqlEntityManagerFactory) {
		return new JpaTransactionManager(mySqlEntityManagerFactory);
	}

	@Bean
	public DataSourceInitializer mySqldataSourceInitializer(@Qualifier("mySqlDataSource") DataSource mySqlDataSource) {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("mysql/schema-mysql.sql"));
		resourceDatabasePopulator.addScript(new ClassPathResource("mysql/data-mysql.sql"));

		DataSourceInitializer mySqldataSourceInitializer = new DataSourceInitializer();
		mySqldataSourceInitializer.setDataSource(mySqlDataSource);
		mySqldataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		return mySqldataSourceInitializer;
	}

}
