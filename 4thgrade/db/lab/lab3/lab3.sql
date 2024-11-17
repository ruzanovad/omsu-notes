-- Создание базы данных
--CREATE DATABASE CLIENTS;
--
-- подключиться
--\connect clients;

-- Создание таблиц
CREATE TABLE Clients1 (
    id_client INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    email VARCHAR(50) UNIQUE, -- index is autogenerated (why?)
    age INT NOT NULL check (age >= 18 AND age <=100),
    city VARCHAR(50),
    registration_date DATE NOT NULL
);

CREATE TABLE Clients2 (
    id_client INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    age SMALLSERIAL NOT NULL CHECK (age >= 18 AND age <=100),
    city VARCHAR(50),
    phone VARCHAR(11) UNIQUE, -- index is autogenerated (why?)
    last_visit_date DATE NOT NULL
);

-- Создание индексов (доп)
CREATE INDEX ix_city_age1 ON Clients1(city, age);
CREATE INDEX ix_city_age2 ON Clients2(city, age);


-- Вставка данных в таблицу Clients1
insert
	into
	Clients1 (name,
	email,
	age,
	city,
	registration_date)
values
('Иван',
'ivanov@example.com',
25,
'Москва',
'2024-11-01'),
('Петр',
'petrov@example.com',
30,
'Санкт-Петербург',
'2024-11-05'),
('Светлана',
'svetlana@example.com',
40,
'Новосибирск',
'2024-10-25'),
('Анна',
'anna@example.com',
22,
'Москва',
'2024-11-10'),
('Игорь',
'igor@example.com',
35,
'Казань',
'2024-11-15'),
('Алексей',
'alexey@example.com',
28,
'Владивосток',
'2024-10-20'),
('Мария',
'maria@example.com',
50,
'Екатеринбург',
'2024-11-12'),
('Дмитрий',
'dmitriy@example.com',
18,
'Самара',
'2024-10-30'),
('Ольга',
'olga@example.com',
45,
'Москва',
'2024-11-03'),
('Екатерина',
'ekaterina@example.com',
32,
'Ростов-на-Дону',
'2024-11-14'),
('Виктор',
'viktor@example.com',
29,
'Новосибирск',
'2024-11-09'),
('Татьяна',
'tatiana@example.com',
41,
'Казань',
'2024-10-18'),
('Максим',
'maxim@example.com',
35,
'Москва',
'2024-11-02'),
('Елена',
'elena@example.com',
23,
'Сочи',
'2024-11-11'),
('Николай',
'nikolay@example.com',
37,
'Калининград',
'2022-10-28');

-- Вставка данных в таблицу Clients2
INSERT INTO Clients2 (name, phone, age, city, last_visit_date) VALUES
('Иван', '89876543210', 25, 'Москва', '2024-11-10'),
('Петр', '89123456789', 30, 'Санкт-Петербург', '2024-11-12'),
('Светлана', '89215678901', 40, 'Новосибирск', '2024-11-07'),
('Анна', '89119876543', 23, 'Екатеринбург', '2024-11-05'),
('Игорь', '89991234567', 35, 'Москва', '2024-11-14'),
('Алексей', '89273456789', 28, 'Красноярск', '2024-10-22'),
('Мария', '89281234567', 55, 'Владивосток', '2024-11-13'),
('Дмитрий', '89174561234', 27, 'Самара', '2024-11-01'),
('Ольга', '89192345678', 45, 'Санкт-Петербург', '2024-10-29'),
('Екатерина', '89982345671', 32, 'Ростов-на-Дону', '2024-11-16'),
('Виктор', '89112345677', 31, 'Екатеринбург', '2024-11-08'),
('Татьяна', '89991234579', 42, 'Москва', '2024-11-04'),
('Максим', '89175671234', 34, 'Казань', '2024-10-27'),
('Елена', '89992347811', 23, 'Владивосток', '2024-11-15'),
('Николай', '89192873456', 19, 'Калининград', '2024-10-31'),
('Николай',
'89192873457',
37,
'Калининград',
'2024-10-28');


-- 1 Объединение данных
with CombinedClients as (
select
	id_client,
	name,
	email as contact,
	age,
	city,
	registration_date as activity_date
from
	Clients1
union all
select
	id_client,
	name,
	phone as contact,
	age,
	city,
	last_visit_date as activity_date
from
	Clients2
),
RankedClients as (
select
	id_client,
	name,
	contact,
	age,
	city,
	activity_date,
	row_number() over (partition by name,
	age
order by
	activity_date desc) as rn
from
	CombinedClients
)
select
	id_client,
	name,
	contact "email/phone",
	age,
	city,
	activity_date "registration_date/last_visit_date"
from
	RankedClients
where
	rn = 1;

-- 2 Уникальные записи
SELECT DISTINCT name, city
FROM (
    SELECT name, city FROM clients1 c 
    UNION
    SELECT name, city FROM clients2 c2 
) AS CombinedClients;

-- 3 Присутствуют в 1 таблице, но отсутствуют во второй 
SELECT c1.name, c1.email, c1.age, c1.city, c1.registration_date
FROM  clients1 c1
LEFT JOIN clients2 c2
    ON c1.name = c2.name AND c1.age = c2.age AND c1.city = c2.city
WHERE c2.name IS NULL
ORDER BY c1.age DESC;

-- 4 клиенты из обеих таблиц, сгруппированные по городу
select
	city,
	count(case when age < 20 then 1 end) "<20",
	count(case when age >= 20 and age < 30 then 1 end) "20-30",
	count(case when age >= 30 then 1 end) ">30"
from
	(
	select
		c1.name,
		c1.age,
		c1.city
	from
		clients1 c1
	join clients2 c2
    on
		c1.name = c2.name
		and c1.age = c2.age
		and c1.city = c2.city)
group by
	city;

-- 5 <25 в первой и >35 во второй
select
	id_client,
	name,
	email as contact,
	age,
	city,
	registration_date as activity_date
from
	Clients1
where
	age < 25
union all
select
	id_client,
	name,
	phone as contact,
	age,
	city,
	last_visit_date as activity_date
from
	Clients2
where
	age >35;

-- 6 клиенты с совпадающим именем но разными городами
SELECT 
    c1.name AS client_name,
    c1.city AS city_in_table1,
    c2.city AS city_in_table2,
    c1.registration_date AS registration_date,
    c2.last_visit_date AS last_visit_date
FROM clients1 c1
JOIN clients2 c2
    ON c1.name = c2.name
WHERE c1.city <> c2.city 
ORDER BY client_name;

-- 7 клиенты из обеих таблиц
select
	c1.name as client_name,
	c1.city as city_in_table1,
	c1.registration_date as registration_date,
	c2.last_visit_date as last_visit_date
from
	clients1 c1
join clients2 c2
on c1.name = c2.name
		and c1.age = c2.age
		and c1.city = c2.city
where
	c1.age > 30
	and current_date - interval '1 year'> c1.registration_date 
	and current_date - interval '6 months'< c2.last_visit_date;
;

-- 8 уникальные клиенты 
SELECT name, age, city, registration_date AS activity_date
FROM clients1
WHERE (name, age, city) IN (
    SELECT name, age, city
    FROM clients2
)
union -- если было бы all, то были бы не уникальные
SELECT name, age, city, last_visit_date AS activity_date
FROM clients2
WHERE (name, age, city) IN (
    SELECT name, age, city
    FROM clients1
)
ORDER BY activity_date ASC;

-- 9 из 1 таблицы у которых есть однофамильцы (??) во 2 с разницей 10 лет
select
	c.name,
	c.age,
	c.city
from
	clients1 c
join clients2 c2 
on
	c.name = c2.name
	and abs(c.age - c2.age) >= 10;

-- 10 последний визит был в течение последнего месяца
select
	name,
	email as contact,
	age,
	city,
	registration_date as last_activity,
	'Clients1' as source
from
	Clients1
where
	registration_date >= CURRENT_DATE - interval '1 month'
union all
select
	name,
	phone as contact,
	age,
	city,
	last_visit_date as last_activity,
	'Clients2' as source
from
	Clients2
where
	last_visit_date >= CURRENT_DATE - interval '1 month'
order by
	last_activity desc
;