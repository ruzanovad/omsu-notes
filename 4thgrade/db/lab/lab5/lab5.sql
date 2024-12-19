create table products (
    id INT  primary key generated always as identity,
    name varchar(64) not null,
    category varchar(100) not null, 
    price decimal(10, 2) not null,
    creation_date date not null
);

create table orders (
    id INT  primary key generated always as identity,
    amount int not null 
    creation_date date not null,
    status varchar(50) CHECK (status IN ('завершен', 'отменен', 'в обработке')),
    product_id INT NOT NULL,  
    FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);


CREATE INDEX idx_category ON Products (category);
CREATE INDEX idx_order_date ON Orders (order_date);

INSERT INTO Products (name, category, price, added_date)
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
    ('Product H', 'Food', 75.00, '2021-06-04');
    


INSERT INTO Orders (product_id, quantity, order_date, status)
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

select p.id, p.category, p.name, p.price, p.creation_date
from products p
join 
(SELECT category, 
        first_value(id) OVER (partition by category order by price) as min_id, 
        last_value(id) OVER (partition by category order by price) as max_id
FROM products) pp
where p.id = pp.min_id or p.id = p.max_id
order by p.category, p.price;

-- 3 

SELECT p.id, p.name, p.category, p.price, p.added_date
FROM Products p
LEFT JOIN Orders o ON p.id = o.product_id
WHERE o.product_id IS NULL
ORDER BY p.added_date DESC;

-- 4

SELECT p.id, p.name, p.category, p.price
FROM Products p
JOIN Orders o ON p.id = o.product_id
WHERE o.status = 'завершен' AND EXTRACT(YEAR FROM o.order_date) = 2023
GROUP BY p.id, p.name, p.category, p.price
HAVING p.price > AVG(o.quantity * p.price);

-- 5

SELECT p.category, p.id, p.name, SUM(o.quantity) AS total_quantity
FROM Products p
JOIN Orders o ON p.id = o.product_id
WHERE o.status = 'завершен'
GROUP BY p.category, p.id, p.name
HAVING SUM(o.quantity) > 50
ORDER BY p.category, total_quantity DESC;

-- 6

select p.id, p.name, p.category
from products
join (select * from orders where amount > 10) on p.id = o.product_id;

-- 7



-- 8

-- 9