package com.purnima.jain.multidatasource.jpa.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CUSTOMER_MYSQL")
@Data
public class CustomerMySqlEntity {
	
	@Id
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;

}
