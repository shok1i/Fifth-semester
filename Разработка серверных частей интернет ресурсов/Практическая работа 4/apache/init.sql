CREATE DATABASE IF NOT EXISTS appDB;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';
GRANT SELECT,UPDATE,INSERT,DELETE ON appDB.* TO 'user'@'%';
FLUSH PRIVILEGES;

USE appDB;

CREATE TABLE IF NOT EXISTS Menu   (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(5, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(5, 2) NOT NULL
);

INSERT INTO Menu (name, price) VALUES
    ('Эспрессо', 2.50),
    ('Американо', 3.00),
    ('Капучино', 3.50),
    ('Латте', 4.00),
    ('Мокка', 4.50),
    ('Раф', 4.00),
    ('Чай черный', 2.00),
    ('Чай зеленый', 2.50),
    ('Какао', 3.00),
    ('Горячий шоколад', 3.50),
    ('Смузи', 4.50),
    ('Торт Наполеон', 3.00),
    ('Пирожное Эклер', 2.50),
    ('Круассан', 2.00),
    ('Песочный пирог', 2.50);


INSERT INTO Orders (name, price) VALUES
    ('Алексей', 3.00),
    ('Мария', 4.50),
    ('Иван', 2.50),
    ('Светлана', 3.50),
    ('Дмитрий', 4.00),
    ('Екатерина', 3.00),
    ('Анна', 4.00),
    ('Павел', 2.00),
    ('Ольга', 3.50),
    ('Сергей', 3.00),
    ('Анастасия', 4.50),
    ('Николай', 3.50),
    ('Татьяна', 2.50),
    ('Максим', 4.00),
    ('Виктория', 2.00);