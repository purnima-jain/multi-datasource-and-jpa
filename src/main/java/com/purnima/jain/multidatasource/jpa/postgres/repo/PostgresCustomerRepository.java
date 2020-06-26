package com.purnima.jain.multidatasource.jpa.postgres.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purnima.jain.multidatasource.jpa.postgres.entity.CustomerPostgresEntity;

@Repository
public interface PostgresCustomerRepository extends JpaRepository<CustomerPostgresEntity, Integer> {
}
