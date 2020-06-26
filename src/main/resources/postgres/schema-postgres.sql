-- Database: my_postgres_db
-- DROP DATABASE my_postgres_db;
-- Creating the database: my_postgres_db
-- CREATE DATABASE my_postgres_db
--     WITH 
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'English_India.1252'
--     LC_CTYPE = 'English_India.1252'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;

-- Drop the table: customer_postgres
DROP TABLE IF EXISTS customer_postgres;

-- Creating the table: customer_postgres
CREATE TABLE customer_postgres
(
	customer_id integer NOT NULL,
	first_name VARCHAR(100), 
	last_name VARCHAR(100),
	PRIMARY KEY (customer_id)
);