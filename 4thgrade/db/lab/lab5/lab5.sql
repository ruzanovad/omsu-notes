create table products (
    id INT  primary key generated always as identity,
    name varchar(64) not null,
    category varchar(100) not null, 
    price decimal(10, 2) not null,
    creation_date date not null
);

create table orders (
    id INT primary key generated always as identity,
    amount int not null,
    creation_date date not null,
    status varchar(50) check (status in ('завершен', 'отменен', 'в обработке')),
    product_id INT not null,  
    foreign key (product_id) references Products(id) on
delete
	cascade
);


CREATE INDEX idx_category ON Products (category);
CREATE INDEX idx_order_date ON Orders (creation_date);

INSERT INTO Products (name, category, price, creation_date)
VALUES 
    ('Product A', 'Electronics', 100.00, '2022-06-01'),
    ('Product B', 'Electronics', 101.00, '2022-06-02'), 
    ('Product C', 'Clothing', 50.00, '2022-06-03'),
    ('Product CC', 'Clothing', 500.00, '2022-06-03'),
    ('Product D', 'Food', 75.00, '2022-06-04'),
    ('Product DD', 'Clothing', 1.00, '2021-06-04'),
    ('Product E', 'Home', 120.00, '2022-06-05'),
    ('Product EE', 'Food', 26.00, '2023-01-05'),
    ('Product F', 'Books', 50.00, '2022-06-03'),
    ('Product G', 'Clothing', 500.00, '2022-09-10'),
    ('Product H', 'Food', 75.00, '2021-06-04'),
   ('Product HH', 'Food', 144000, '2021-06-04');
    

INSERT INTO Orders (product_id, amount, creation_date, status)
VALUES 
    (8, 52, '2024-05-12', 'в обработке'), 
    (8, 52, '2023-05-12', 'завершен'), 
    (8, 100, '2024-05-11', 'отменен'), 
    (1, 30, '2023-01-10', 'завершен'),  
    (1, 25, '2024-02-11', 'отменен'),  
    (2, 15, '2023-03-12', 'в обработке'), 
    (3, 100, '2024-04-13', 'завершен'),
    (2, 15, '2024-05-12', 'в обработке'), 
    (3, 10, '2023-06-13', 'завершен'),  
    (3, 20, '2023-07-14', 'отменен'),   
    (4, 30, '2023-08-15', 'завершен'),  
    (5, 5,  '2024-09-16', 'в обработке'), 
    (6, 51,  '2024-10-16', 'в обработке'), 
    (7, 2,  '2024-11-17', 'завершен'), 
    (7, 1,  '2024-12-17', 'отменен'); 

-- 1

SELECT p.id, p.name, p.category, p.price
FROM Products p
JOIN (
    SELECT category, AVG(price) AS avg_price
    FROM Products
    GROUP BY category
) AS avg_prices
ON p.category = avg_prices.category -- среднее в категории
WHERE p.price > avg_prices.avg_price
ORDER BY p.price DESC;

-- 2

--with pp as (
select p.id,p.category, p.price, p.name
from products p 
join (select category, min(price), max(price)
from products p 
group by category) pp 
on pp.category = p.category and (p.price = pp.min or p.price = pp.max)
order by category, price;

-- 3 

SELECT p.id, p.name, p.category, p.price, p.creation_date 
FROM Products p
LEFT JOIN Orders o ON p.id = o.product_id
WHERE o.product_id IS NULL
ORDER BY p.creation_date DESC;

-- 4

WITH AvgOrderPrice AS (
    SELECT AVG(o.amount*p.price) AS avg_price
    FROM Orders o
    join products p on o.product_id = p.id 
    WHERE o.status = 'завершен'
      AND EXTRACT(YEAR FROM o.creation_date) = 2023
)
SELECT p.id, p.name, p.category, p.price, AvgOrderPrice.avg_price
FROM Products p
cross JOIN AvgOrderPrice 
WHERE p.price > AvgOrderPrice.avg_price;

-- 5

SELECT 
    p.id AS product_id,
    p.name AS product_name,
    p.category,
    COUNT(o.id) AS completed_orders_count
FROM 
    Products p
JOIN 
    Orders o
ON 
    p.id = o.product_id
WHERE 
    o.status = 'завершен'
GROUP BY 
    p.id, p.name, p.category
HAVING 
    COUNT(o.id) > 50
ORDER BY 
    p.category, completed_orders_count DESC;

-- 6

select
	p.id,
	p.name,
	p.category
from
	products p
join (
	select
		*
	from
		orders
	where
		amount > 10) o on
	p.id = o.product_id;

-- 7
-- Выведите продукты, которые были заказаны в каждом месяце 2023 года, с
-- указанием количества заказов в каждом месяце.

with all_Products as(
select
	c.id,
	c.name,
	extract(month
from
	o.creation_date) as order_month,
	COUNT(o.id) as orders_count
from
	Products c
left join 
    Orders o
on
	c.id = o.product_id
where
	extract(year
from
	o.creation_date) = 2023
group by
	c.id,
	c.name,
	extract(month
from
	o.creation_date))
select
	*
from
	all_Products
where
	id in (
	select
		id
	from
		all_Products
	group by
		id
	having
		count(distinct order_month) = 12);
	
-- 8
	
WITH AvgPrice AS (
    SELECT AVG(price) AS avg_price
    FROM Products
),
NeverCancelled AS (
    SELECT 
        p.id
    FROM 
        Products p
    LEFT JOIN 
        Orders o
    ON 
        p.id = o.product_id AND o.status = 'отменен'
    WHERE 
        o.id IS NULL
)
SELECT 
    p.id, 
    p.name, 
    p.category, 
    p.price
FROM 
    Products p
JOIN 
    AvgPrice ap
ON 
    p.price > ap.avg_price
JOIN 
    NeverCancelled nc
ON 
    p.id = nc.id;
   
-- 9
   
WITH StatusCounts AS (
    SELECT 
        p.id AS product_id, 
        p.name AS product_name, 
        o.status,
        COUNT(o.id) AS status_count,
        MIN(o.creation_date) AS first_order_date,
        MAX(o.creation_date) AS last_order_date
    FROM 
        Products p
    JOIN 
        Orders o
    ON 
        p.id = o.product_id
    WHERE 
        o.status IN ('завершен', 'отменен')
    GROUP BY 
        p.id, p.name, o.status
)
SELECT 
    sc.product_id, 
    sc.product_name, 
    MIN(sc.first_order_date) AS first_order_date, 
    MAX(sc.last_order_date) AS last_order_date
FROM 
    StatusCounts sc
GROUP BY 
    sc.product_id, sc.product_name
HAVING 
    COUNT(DISTINCT sc.status) = 2;   
   
-- 10
   
WITH MonthlyProductOrders AS (
    SELECT 
        p.id AS product_id, 
        p.name AS product_name, 
        EXTRACT(YEAR FROM o.creation_date) AS order_year,
        EXTRACT(MONTH FROM o.creation_date) AS order_month,
        SUM(o.amount) AS total_amount
    FROM 
        Products p
    JOIN 
        Orders o
    ON 
        p.id = o.product_id
    GROUP BY 
        p.id, p.name, EXTRACT(YEAR FROM o.creation_date), EXTRACT(MONTH FROM o.creation_date)
),
MaxOrders AS (
    SELECT 
        order_year, 
        order_month, 
        MAX(total_amount) AS max_amount
    FROM 
        MonthlyProductOrders
    GROUP BY 
        order_year, order_month
)
SELECT 
    mpo.product_id, 
    mpo.product_name, 
    mpo.order_year, 
    mpo.order_month, 
    mpo.total_amount
FROM 
    MonthlyProductOrders mpo
JOIN 
    MaxOrders mo
ON 
    mpo.order_year = mo.order_year AND 
    mpo.order_month = mo.order_month AND 
    mpo.total_amount = mo.max_amount
order by order_year, order_month;

-- 11

CREATE OR REPLACE FUNCTION check_order_amount()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.amount < 1 THEN
        RAISE EXCEPTION 'Сумма заказа не может быть менее 0';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_no_less_than_one
BEFORE INSERT OR UPDATE ON Orders 
FOR EACH ROW
EXECUTE FUNCTION check_order_amount();

INSERT INTO Orders (product_id, amount, creation_date, status)
VALUES 
(1, 0, '2023-01-15', 'завершен');

-- 12

CREATE OR REPLACE FUNCTION log_price_changes()
RETURNS TRIGGER AS $$
BEGIN
	CREATE TABLE IF NOT EXISTS PriceLogs (
	    id SERIAL PRIMARY KEY,
	    product_id INT NOT NULL,
	    old_price DECIMAL(10, 2),
	    new_price DECIMAL(10, 2),
	    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);
    INSERT INTO PriceLogs (product_id, old_price, new_price)
    VALUES (NEW.id, OLD.price, NEW.price);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER price_update_trigger
AFTER UPDATE OF price ON Products
FOR EACH ROW
WHEN (OLD.price IS DISTINCT FROM NEW.price)
EXECUTE FUNCTION log_price_changes();

UPDATE Products
SET price = price * 1.10
WHERE id = 1;

UPDATE Products
SET price = price * 1.15
WHERE id = 2;

UPDATE Products
SET price = price * 0.90
WHERE id = 3;

select * from PriceLogs;

-- 13
CREATE OR REPLACE PROCEDURE get_total_orders_by_category(
    IN category_name VARCHAR(100),
    OUT total_units INT
)
LANGUAGE plpgsql AS $$
BEGIN
    SELECT SUM(o.amount)
    INTO total_units
    FROM Orders o
    JOIN Products p ON o.product_id = p.id
    WHERE p.category = category_name;
END;
$$;

DO $$ 
DECLARE 
    total_units INT;
BEGIN
    call get_total_orders_by_category('Food', total_units);
    RAISE NOTICE 'Total units: %', total_units;
END $$;

-- 14

CREATE OR REPLACE PROCEDURE cancel_orders_by_product_limit(
    IN product_id INT,
    IN order_limit INT
)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE Orders
    SET status = 'отменен'
    WHERE product_id = product_id
      AND (SELECT COUNT(*) FROM Orders WHERE product_id = product_id) > order_limit;
END;
$$;

-- 15

CREATE OR REPLACE PROCEDURE increase_price_for_large_orders()
LANGUAGE plpgsql AS $$
DECLARE
    order_cursor CURSOR FOR 
        SELECT p.id, p.price
        FROM Products p
        JOIN Orders o ON p.id = o.product_id
        GROUP BY p.id, p.price
        HAVING SUM(o.amount) > 50;
    product_record RECORD;
BEGIN
    OPEN order_cursor;
    LOOP
        FETCH order_cursor INTO product_record;
        EXIT WHEN NOT FOUND;
        UPDATE Products
        SET price = price * 1.05
        WHERE id = product_record.id;
    END LOOP;
    CLOSE order_cursor;
END;
$$;