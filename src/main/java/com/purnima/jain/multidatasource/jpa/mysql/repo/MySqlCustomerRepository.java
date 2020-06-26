package com.purnima.jain.multidatasource.jpa.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purnima.jain.multidatasource.jpa.mysql.entity.CustomerMySqlEntity;

@Repository
public interface MySqlCustomerRepository extends JpaRepository<CustomerMySqlEntity, Integer> {
}
