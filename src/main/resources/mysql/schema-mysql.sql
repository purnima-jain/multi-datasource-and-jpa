-- Creating the database: my_mysql_db
-- CREATE DATABASE my_mysql_db;
-- use my_mysql_db;    
-- show tables;

-- Drop the table: customer_mysql
DROP TABLE IF EXISTS customer_mysql;

-- Creating the table: customer_mysql
CREATE TABLE customer_mysql
(
	customer_id INT NOT NULL,
	first_name VARCHAR(100), 
	last_name VARCHAR(100),
    PRIMARY KEY ( customer_id )
);