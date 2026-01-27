-- Создание базы данных
-- CREATE DATABASE users;

--https://stackoverflow.com/questions/55300370/postgresql-serial-vs-identity

-- Создание таблиц
CREATE TABLE Users (
    id_users primary key GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    email VARCHAR(50) UNIQUE,
    phone VARCHAR(11) UNIQUE,
    registration_date DATE NOT NULL
);

CREATE TABLE Orders (
    id_order primary key GENERATED ALWAYS AS IDENTITY,
    amount INT NOT NULL CHECK (amount >= 0), -- доп требование
    order_date DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    id_users INT REFERENCES Users(id_users) ON DELETE CASCADE
);

-- Создание индексов (доп)
CREATE INDEX ix_email ON Users(email);
CREATE INDEX ix_order_date ON Orders(order_date);

-- Вставка данных в таблицу Users
INSERT INTO Users (name, email, phone, registration_date) VALUES 
('Иван', 'vanya_111@mail.ru', '88005553535', '2024-10-05'),
('Евгений', 'zheka@gmail.com', '89506271802', '2024-09-25'),
('Екатерина', 'ekatya-26@mail.ru', '89915613274', '2024-11-03'),
('Константин', 'steyruiy@mail.ru', '89036546580', '2024-08-30'),
('Денис', 'doref1994@gmail.com', '89135008154', '2024-09-12');

-- Вставка данных в таблицу Orders
INSERT INTO Orders (amount, order_date, status, id_users) VALUES 
(500, '2024-10-06', 'в процессе', 1),
(630, '2024-10-11', 'завершён', 1),
(850, '2024-11-04', 'отменён', 1),
(1000, '2024-10-25', 'в процессе', 2),
(250, '2024-11-01', 'отменён', 3);

-- Запросы
-- 1. Вывод данных пользователей и их заказов
SELECT 
    Users.id_users, Users.name, Users.email, Users.phone, Users.registration_date, 
    Orders.id_order, Orders.amount, Orders.order_date, Orders.status
FROM Users 
JOIN Orders ON Users.id_users = Orders.id_users 
ORDER BY Orders.order_date DESC;

-- 2. Пользователи без заказов
SELECT * 
FROM Users 
WHERE NOT EXISTS (SELECT 1 FROM Orders WHERE Users.id_users = Orders.id_users)
ORDER BY registration_date;

-- 3. Количество и сумма заказов по пользователям
SELECT id_users, COUNT(id_users) count, SUM(amount) sum 
FROM Orders 
GROUP BY id_users;

-- 4. Заказы с сортировкой по статусу и дате
SELECT 
    Users.*, Orders.id_order, Orders.amount, Orders.order_date, Orders.status
FROM Users 
JOIN Orders ON Users.id_users = Orders.id_users 
ORDER BY Orders.status DESC, Orders.order_date DESC;

-- 5. Пользователи с суммой заказов более 1000
SELECT 
    Users.id_users, Users.name, SUM(Orders.amount) AS sum
FROM Users
JOIN Orders ON Users.id_users = Orders.id_users
GROUP BY Users.id_users
HAVING SUM(Orders.amount) > 1000;

-- 6. Пользователи с заказами со статусом "отменён"
-- https://stackoverflow.com/questions/7633086/why-is-there-a-select-1-from-table
SELECT * 
FROM Users 
WHERE EXISTS (
    SELECT 1 
    FROM Orders 
    WHERE Users.id_users = Orders.id_users AND status = 'отменён'
);

-- 7. Минимальная сумма заказа более 500
SELECT 
    Users.id_users, Users.name, MIN(Orders.amount) AS min_amount
FROM Users
JOIN Orders ON Users.id_users = Orders.id_users
GROUP BY Users.id_users
HAVING MIN(Orders.amount) > 500;

-- 8. Пользователи с заказами в течение 30 дней после регистрации
SELECT * 
FROM Users 
WHERE EXISTS (
    SELECT 1 
    FROM Orders 
    WHERE Users.id_users = Orders.id_users 
    AND Orders.order_date <= Users.registration_date + INTERVAL '30 days'
);

-- 9. Пользователи без заказов за последние 6 месяцев и зарегистрированные более года назад
SELECT * 
FROM Users 
WHERE (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM registration_date)) > 1 
AND NOT EXISTS (
    SELECT 1 
    FROM Orders 
    WHERE Users.id_users = Orders.id_users 
    AND EXTRACT(MONTH FROM CURRENT_DATE) - EXTRACT(MONTH FROM order_date) < 6
);

-- 10. Пользователи с заказами в текущем месяце
SELECT * 
FROM Users 
WHERE EXISTS (
    SELECT 1 
    FROM Orders 
    WHERE Users.id_users = Orders.id_users 
    AND EXTRACT(MONTH FROM CURRENT_DATE) = EXTRACT(MONTH FROM order_date)
);
