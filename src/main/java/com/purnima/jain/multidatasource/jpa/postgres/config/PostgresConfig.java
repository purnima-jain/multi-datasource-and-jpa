package com.purnima.jain.multidatasource.jpa.postgres.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		entityManagerFactoryRef = "postgresEntityManagerFactory", 
		transactionManagerRef = "postgresTransactionManager", 
		basePackages = { "com.purnima.jain.multidatasource.jpa.postgres" })
public class PostgresConfig {

	@Bean(name = "postgresDataSource")
	@ConfigurationProperties(prefix = "spring.postgres.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "postgresEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("postgresDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("com.purnima.jain.multidatasource.jpa.postgres.entity")
				.build();
	}

	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory postgresEntityManagerFactory) {
		return new JpaTransactionManager(postgresEntityManagerFactory);
	}

	@Bean
	public DataSourceInitializer postgresDataSourceInitializer(@Qualifier("postgresDataSource") DataSource postgresDataSource) {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(new ClassPathResource("postgres/schema-postgres.sql"));
		resourceDatabasePopulator.addScript(new ClassPathResource("postgres/data-postgres.sql"));

		DataSourceInitializer postgresDataSourceInitializer = new DataSourceInitializer();
		postgresDataSourceInitializer.setDataSource(postgresDataSource);
		postgresDataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		return postgresDataSourceInitializer;
	}

}
