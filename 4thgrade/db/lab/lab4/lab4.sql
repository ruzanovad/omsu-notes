CREATE TABLE Clients (
    id_client primary key GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    age INTEGER check (age >= 18)
    email VARCHAR(50) UNIQUE,
    -- phone VARCHAR(11) UNIQUE,
    registration_date DATE NOT NULL
);

CREATE TYPE status_type AS ENUM ('в процессе', 'завершен', 'отменен');

CREATE TABLE Orders (
    id_order primary key GENERATED ALWAYS AS IDENTITY,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0.0), 
    order_date DATE NOT NULL,
    status status_type NOT NULL,
    id_users INT REFERENCES Clients(id_client) ON DELETE CASCADE
);

-- Создание индексов (доп)
CREATE INDEX ix_email ON Clients(email);
CREATE INDEX ix_order_date ON Orders(order_date);

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

-- 1

select count(*)
from Orders
where status = 'завершен';

-- 2

select id_client, count(case WHEN status = 'завершен' THEN 1 END) "Завершенные", 
count(case WHEN status = 'отменен' THEN 1 END) "Отмененные", 
from Orders
group by id_client

-- 3
select avg(amount)
from Orders
where status = 'завершен' and current_date - order_date <= INTERVAL '6 months'

-- 4

select id_client,AVG(amount)
from Orders
group by id_client
where 
having sum(amount) >= 500;

-- 5

select client_id, status, sum(amount)
from Clients
group by client_id, status;

-- 6 
select client_id, max(amount), min(amount)
from Clients
where status = 'завершен'
group by client_id;

-- 7

