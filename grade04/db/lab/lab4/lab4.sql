CREATE TABLE Clients (
    id_client int primary key GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(40) NOT NULL,
    age INTEGER check (age >= 18),
    email VARCHAR(255) UNIQUE,
    -- phone VARCHAR(11) UNIQUE,
    registration_date DATE NOT NULL
);

CREATE TYPE status_type AS ENUM ('в процессе', 'завершен', 'отменен');

CREATE TABLE Orders (
    id_order int primary key GENERATED ALWAYS AS IDENTITY,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0.0), 
    order_date DATE NOT NULL,
    status status_type NOT NULL,
    id_users int not null,
    foreign key (id_users) REFERENCES Clients(id_client) ON DELETE CASCADE
);

-- Создание индексов (доп)
CREATE INDEX ix_email ON Clients(email);
CREATE INDEX ix_order_date ON Orders(order_date);

-- Вставка данных в Clients
INSERT INTO Clients (name, email, registration_date, age)
VALUES 
('Иван Иванов', 'ivan.ivanov@example.com', '2022-01-10', 25),  -- Младше 30
('Петр Петров', 'petr.petrov@example.com', '2023-01-05', 35),  -- От 30 до 50
('Анна Смирнова', 'anna.smirnova@example.com', '2023-03-01', 55), -- Старше 50
('Григорий Распутин', 'rasputin@example.com', '2003-01-10', 25); --  без заказов

-- Вставка данных в Orders
INSERT INTO Orders (id_users, amount, order_date, status)
VALUES 
-- Заказы за 2023 год
(1, 100, '2023-01-15', 'завершен'),
(2, 20, '2023-02-10', 'в процессе'),
(3, 150, '2023-03-05', 'завершен'),
(1, 300, '2023-04-20', 'отменен'),
(2, 25, '2023-05-10', 'завершен'),
(3, 180, '2023-06-15', 'в процессе'),
(1, 120, '2023-07-20', 'завершен'),
(2, 22, '2023-08-18', 'завершен'),
(3, 190, '2023-09-10', 'отменен'),
(1, 330, '2023-10-05', 'завершен'),
(2, 30, '2023-11-12', 'в процессе'),
(3, 400, '2023-12-25', 'завершен'),
-- Заказы за 2024 год
(1, 150, '2024-01-15', 'завершен'),
(1, 150, '2024-02-15', 'завершен'),
(1, 150, '2024-03-15', 'завершен'),
(1, 150, '2024-05-15', 'завершен'),
(1, 150, '2024-06-15', 'завершен'),
(1, 150, '2024-08-15', 'завершен'),
(1, 150, '2024-09-15', 'завершен'),
(1, 150, '2024-12-15', 'завершен'),
(1, 150, '2024-11-15', 'завершен'),
(2, 17, '2024-02-10', 'в процессе'),
(3, 130, '2024-03-05', 'завершен'),
(1, 200, '2024-04-20', 'в процессе'),
(2, 25, '2024-05-10', 'отменен'),
(3, 180, '2024-06-15', 'завершен'),
(1, 120, '2024-07-20', 'в процессе'),
(2, 32, '2024-08-18', 'завершен'),
(3, 190, '2024-09-10', 'отменен'),
(1, 430, '2024-10-05', 'завершен'),
(2, 30, '2024-11-12', 'в процессе'),
(3, 500, '2024-12-01', 'завершен');

-- 1

select count(*) total_count
from Orders
where status = 'завершен';

-- 2

SELECT 
    c.id_client,
    c.name,
    count(CASE WHEN o.status = 'завершен' THEN 1 END) AS completed_orders,
    count(CASE WHEN o.status = 'отменен' THEN 1 END) AS canceled_orders
FROM Clients c
LEFT JOIN Orders o ON c.id_client = o.id_users
GROUP BY c.id_client, c.name;

-- 3
select avg(amount)
from Orders
where status = 'завершен' and current_date - INTERVAL '6 months' <= order_date;

-- 4

SELECT 
    c.id_client,
    c.name,
    AVG(o.amount)  avg_completed_amount
FROM Clients c
LEFT JOIN Orders o ON c.id_client = o.id_users
WHERE o.status = 'завершен'
GROUP BY c.id_client, c.name
HAVING SUM(o.amount) >= 500;

-- 5

SELECT 
    c.id_client,
    c.name,
    o.status,
    SUM(o.amount) total_amount
FROM Clients c
LEFT JOIN Orders o ON c.id_client = o.id_users
GROUP BY c.id_client, c.name, o.status
ORDER BY c.id_client, o.status;


-- 6 
SELECT 
    c.id_client,
    c.name,
    MAX(o.amount) max_completed_amount,
    MIN(o.amount) min_completed_amount
FROM Clients c
JOIN Orders o ON c.id_client = o.id_users
WHERE o.status = 'завершен'
GROUP BY c.id_client, c.name;

-- 7

SELECT 
    EXTRACT(YEAR FROM order_date) AS year,
    EXTRACT(MONTH FROM order_date) AS month,
    status,
    COUNT(*) AS order_count
FROM Orders
GROUP BY EXTRACT(YEAR FROM order_date), EXTRACT(MONTH FROM order_date), status
ORDER BY year, month, status;

-- 8

SELECT 
    c.id_client,
    c.name,
    MIN(o.order_date) earliest_canceled_order
FROM Clients c
LEFT JOIN Orders o ON c.id_client = o.id_users
WHERE o.status = 'отменен'
GROUP BY c.id_client, c.name;

-- 9

WITH avg_order AS (
    SELECT AVG(amount) AS overall_avg
    FROM Orders
)
SELECT 
    c.id_client,
    c.name,
    o.amount AS order_amount
FROM Clients c
LEFT JOIN Orders o ON c.id_client = o.id_users
CROSS JOIN avg_order
WHERE o.amount > avg_order.overall_avg;

-- 10
with all_clients as(
select
	c.id_client,
	c.name,
	extract(month
from
	o.order_date) as order_month,
	COUNT(o.id_order) as orders_count
from
	Clients c
left join 
    Orders o
on
	c.id_client = o.id_users
where
	extract(year
from
	o.order_date) = extract(year
from
	CURRENT_DATE)
group by
	c.id_client,
	c.name,
	extract(month
from
	o.order_date))
select
	*
from
	all_clients
where
	id_client in (
	select
		id_client
	from
		all_clients
	group by
		id_client
	having
		count(distinct order_month) = 12);


-- 11

CREATE OR REPLACE FUNCTION check_order_amount()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.amount < 0 THEN
        RAISE EXCEPTION 'Сумма заказа не может быть менее 0';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_no_negative
BEFORE INSERT OR UPDATE ON Orders 
FOR EACH ROW
EXECUTE FUNCTION check_order_amount();

INSERT INTO Orders (id_users, amount, order_date, status)
VALUES 
-- Заказы за 2023 год
(1, -100, '2023-01-15', 'завершен');

-- 12

CREATE OR REPLACE PROCEDURE get_clients_and_order_sum_by_age_group(
    IN age_group TEXT,                 -- Параметр: возрастная группа
    OUT total_clients INT,             -- Выходной параметр: количество клиентов
    OUT total_order_amount NUMERIC     -- Выходной параметр: сумма заказов
)
LANGUAGE plpgsql AS $$
BEGIN
    -- Инициализируем переменные
    total_clients := 0;
    total_order_amount := 0;

    -- Определяем условия для возрастных групп
    IF age_group = 'младше 30' THEN
        SELECT 
            COUNT(DISTINCT c.id_client), COALESCE(SUM(o.amount), 0)
        INTO total_clients, total_order_amount
        FROM Clients c
        LEFT JOIN Orders o ON c.id_client = o.id_users
        WHERE c.age < 30;

    ELSIF age_group = 'от 30 до 50' THEN
        SELECT 
            COUNT(DISTINCT c.id_client), COALESCE(SUM(o.amount), 0)
        INTO total_clients, total_order_amount
        FROM Clients c
        LEFT JOIN Orders o ON c.id_client = o.id_users
        WHERE c.age BETWEEN 30 AND 50;

    ELSIF age_group = 'старше 50' THEN
        SELECT 
            COUNT(DISTINCT c.id_client), COALESCE(SUM(o.amount), 0)
        INTO total_clients, total_order_amount
        FROM Clients c
        LEFT JOIN Orders o ON c.id_client = o.id_users
        WHERE c.age > 50;

    ELSE
        RAISE EXCEPTION 'Некорректная возрастная группа. Допустимые значения: младше 30, от 30 до 50, старше 50';
    END IF;
END;
$$;

DO $$ 
DECLARE 
    v_total_clients INT;
    v_total_order_amount NUMERIC;
BEGIN
    CALL get_clients_and_order_sum_by_age_group('старше 50', v_total_clients, v_total_order_amount);
    RAISE NOTICE 'Total clients: %', v_total_clients;
    RAISE NOTICE 'Total order amount: %', v_total_order_amount;
END $$;


-- 13

select c.name, c.age, o.amount 
FROM Clients c
join Orders o
on o.id_users = c.id_client
where c.age > 50;


CREATE OR REPLACE PROCEDURE apply_discount_for_senior_clients()
LANGUAGE plpgsql AS $$
BEGIN
    -- Обновляем сумму заказа, уменьшая её на 10%, для клиентов старше 50 лет
    UPDATE Orders o
    SET amount = amount * 0.9
    FROM Clients c
    WHERE o.id_users = c.id_client
      AND c.age > 50;

    RAISE NOTICE 'Скидка 10%% применена для клиентов старше 50 лет.';
END;
$$;

call apply_discount_for_senior_clients();

select c.name, c.age, o.amount 
FROM Clients c
join Orders o
on o.id_users = c.id_client
where c.age > 50;

-- 14

CREATE OR REPLACE PROCEDURE transfer_orders(
    IN from_id_client INT,
    IN to_id_client INT
)
LANGUAGE plpgsql AS $$
DECLARE
    from_exists BOOLEAN;
    to_exists BOOLEAN;
BEGIN

    SELECT EXISTS(SELECT 1 FROM Clients WHERE id_client = from_id_client) INTO from_exists;
    SELECT EXISTS(SELECT 1 FROM Clients WHERE id_client = to_id_client) INTO to_exists;


    IF NOT from_exists THEN
        RAISE EXCEPTION 'Клиент с ID % не существует.', from_id_client;
    ELSIF NOT to_exists THEN
        RAISE EXCEPTION 'Клиент с ID % не существует.', to_id_client;
    END IF;

    UPDATE Orders
    SET id_users = to_id_client
    WHERE id_users = from_id_client;

    RAISE NOTICE 'Заказы клиента % успешно перенесены к клиенту %.', from_id_client, to_id_client;

EXCEPTION
    WHEN OTHERS THEN

        RAISE EXCEPTION 'Ошибка при переносе заказов: %', SQLERRM;
END;
$$;


CALL transfer_orders(1, 4);