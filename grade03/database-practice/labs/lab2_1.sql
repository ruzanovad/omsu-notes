SELECT locality_name
FROM localities
WHERE population >= 500000;
SELECT locality_name
FROM localities
WHERE area <= 500;
-- except
SELECT locality_name
FROM localities
WHERE population >= 500000
EXCEPT
SELECT locality_name
FROM localities
WHERE area <= 500;
-- intersect
SELECT locality_name
FROM localities
WHERE population >= 500000
INTERSECT
SELECT locality_name
FROM localities
WHERE area <= 500;
-- union
SELECT locality_name
FROM localities
WHERE population >= 1000000
UNION
SELECT locality_name
FROM localities
WHERE area >= 1000;
-- division
-- division on count
SELECT *
FROM localities
WHERE locality_id in (
        SELECT f_locality_id
        FROM roads
        GROUP BY f_locality_id
        HAVING count(s_locality_id) = (
                SELECT count(*)
                FROM localities
                WHERE locality_type_id = 1
            )
    );
-- equi join
SELECT l1.locality_name,
    l2.locality_name
FROM localities as l1
    inner join roads as r on l1.locality_id = r.f_locality_id
    inner join localities as l2 on r.s_locality_id = l2.locality_id;
-- natural join
SELECT *
FROM localities
    natural join locality_trip_descriptions;
-- composition
SELECT locality_name,
    description
FROM localities as l,
    locality_trip_descriptions as d
WHERE l.locality_trip_description_id = d.locality_trip_description_id;
-- inner join
SELECT *
FROM localities as l
    inner join locality_trip_descriptions as d on l.locality_trip_description_id = d.locality_trip_description_id;
-- outer join
SELECT *
FROM localities as l
    left outer join locality_trip_descriptions as d on l.locality_trip_description_id = d.locality_trip_description_id;
SELECT *
FROM localities as l
    right outer join locality_trip_descriptions as d on l.locality_trip_description_id = d.locality_trip_description_id;
-- using
SELECT *
FROM localities as l
    right outer join locality_trip_descriptions as d using(locality_trip_description_id);
-- cross join
SELECT *
FROM locality_types
    cross join road_types;
-- left join
SELECT *
FROM localities as l1
    left join roads as r on l1.locality_id = r.f_locality_id;
--right join
SELECT *
FROM localities as l1
    right join roads as r on l1.locality_id = r.f_locality_id;
-- self join
SELECT l1.locality_name,
    l1.locality_id,
    l2.locality_id,
    l2.locality_name
FROM localities as l1
    join localities as l2 on l1.locality_id <> l2.locality_id;
-- self join without duplicate pairs
SELECT l1.locality_name as l1_name,
    l1.locality_id as l1_id,
    l2.locality_id as l2_id,
    l2.locality_name as l2_name
FROM localities as l1
    join localities as l2 on l1.locality_id < l2.locality_id
    AND l1.locality_name <> l2.locality_name;