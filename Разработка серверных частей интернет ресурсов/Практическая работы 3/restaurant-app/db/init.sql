-- db/init.sql
CREATE DATABASE restaurant;
USE restaurant;

CREATE TABLE menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(5, 2) NOT NULL
);
