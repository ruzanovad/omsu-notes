-- select without where
select *
from localities;
-- select with all, distinct
select ALL locality_type_id
from localities;
select DISTINCT locality_type_id
FROM localities;
-- select calculated values and aliases
select locality_name, (population / area) as density
from localities;
-- select with where
select locality_name,
    population
FROM localities
WHERE population <= 700000;
-- select with beetween
SELECT locality_name,
    population
FROM localities
WHERE population BETWEEN 400000 AND 600000;
-- select with is [not] null
SELECT locality_name,
    locality_trip_description_id
FROM localities
WHERE locality_trip_description_id IS NULL;
SELECT locality_name,
    locality_trip_description_id
FROM localities
WHERE locality_trip_description_id IS NOT NULL;
-- select with like
SELECT locality_name
FROM localities
WHERE locality_name like 'K%';
SELECT locality_name
FROM localities
WHERE locality_name like 'N%';
SELECT locality_name
FROM localities
WHERE locality_name like '%msk';
-- select with upper, lower
SELECT lower(locality_name)
FROM localities
WHERE length(locality_name) > 5;
SELECT upper(locality_name)
FROM localities
WHERE length(locality_name) <= 5;
-- select with in
SELECT locality_name,
    area
FROM localities
WHERE locality_name in (
        SELECT locality_name
        FROM localities
        WHERE population BETWEEN 400000 AND 600000
    );
-- select with exists
SELECT l1.locality_name,
    l1.population,
    l1.area
from localities as l1
WHERE EXISTS (
        SELECT *
        FROM localities as l2
        WHERE l1.locality_id = l2.locality_id
            and l2.locality_trip_description_id IS NOT NULL
    );
-- select with order by, asc, desc
SELECT locality_name,
    area
FROM localities
ORDER BY area;
SELECT locality_name,
    population
FROM localities
ORDER BY population ASC;
SELECT locality_name,
    population
FROM localities
ORDER BY population DESC;
-- select with count, sum, avg, min, max
SELECT count(*) as count_of_localities
from localities;
SELECT sum(length) as sum_of_all_roads
from roads;
SELECT locality_name,
    population
FROM localities
WHERE population > (
        SELECT avg(population)
        from localities
    );
SELECT min(area) as min_area
from localities;
SELECT max(population) as max_population
from localities;
-- select with group by, having
select locality_type_id,
    count(*)
from localities
GROUP BY locality_type_id;
select locality_name,
    population
from localities
GROUP BY locality_name,
    population
having population > 500000;