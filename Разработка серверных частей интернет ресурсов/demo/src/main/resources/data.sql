DROP SCHEMA demo;
CREATE SCHEMA demo;

INSERT INTO coffees (coffeeid, coffee_name, coffee_description, coffee_price)
VALUES (0, 'Эспрессо', 'Description',2.50),
       (1, 'Американо', 'Description',3.00),
       (2, 'Капучино', 'Description',3.50),
       (3, 'Латте', 'Description',4.00),
       (4, 'Мокка', 'Description',4.50),
       (5, 'Раф', 'Description',4.00),
       (6, 'Чай черный', 'Description',2.00),
       (7, 'Чай зеленый', 'Description',2.50),
       (8, 'Какао', 'Description',3.00),
       (9, 'Горячий шоколад', 'Description',3.50),
       (10, 'Смузи', 'Description',4.50),
       (11, 'Торт Наполеон', 'Description',3.00),
       (12, 'Пирожное Эклер', 'Description',2.50),
       (13, 'Круассан', 'Description',2.00),
       (14, 'Песочный пирог', 'Description',2.50);

INSERT INTO orders (orderid, order_date, customer_name, order_price)
VALUES (0, '14-12-2024', 'Name',2.50),
       (1, '14-12-2024', 'Name',3.00),
       (2, '14-12-2024', 'Name',3.50),
       (3, '14-12-2024', 'Name',4.00),
       (4, '14-12-2024', 'Name',4.50),
       (5, '14-12-2024', 'Name',4.00),
       (6, '14-12-2024', 'Name',2.00),
       (7, '14-12-2024', 'Name',2.50),
       (8, '14-12-2024', 'Name',3.00),
       (9, '14-12-2024', 'Name',3.50),
       (10, '14-12-2024', 'Name',4.50),
       (11, '14-12-2024', 'Name',3.00),
       (12, '14-12-2024', 'Name',2.50),
       (13, '14-12-2024', 'Name',2.00),
       (14, '14-12-2024', 'Name',2.50);