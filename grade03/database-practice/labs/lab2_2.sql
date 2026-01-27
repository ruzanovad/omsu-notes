-- 1
SELECT *
FROM localities
WHERE locality_id IN (
        SELECT f_locality_id
        FROM roads
        GROUP BY f_locality_id
        HAVING count(s_locality_id) > 1
    );
-- 2
SELECT *
FROM localities
WHERE locality_id IN (
        SELECT f_locality_id
        FROM roads
        GROUP BY f_locality_id
        HAVING count(s_locality_id) = (
                SELECT count(*)
                FROM localities
                WHERE locality_type_id = 1
            )
    );
-- 3
select s_locality_id
from roads as r1
group by s_locality_id
having count(f_locality_id) = (
        select count(distinct f_locality_id)
        from roads as r2
        where r2.f_locality_id <> r1.s_locality_id
    );
-- 4
SELECT *
FROM localities
WHERE locality_id NOT IN (
        SELECT f_locality_id
        FROM roads
        WHERE s_locality_id = 1
    );
-- 5
select *
from localities as l1
where l1.locality_id in (
        select r1.f_locality_id
        from roads as r1
        group by r1.f_locality_id
        having r1.f_locality_id = l1.locality_id
            and avg(length) > (
                select avg(av_l)
                from (
                        select avg(r2.length) as av_l
                        from roads as r2
                        where r2.f_locality_id <> l1.locality_id
                        group by r2.f_locality_id
                    )
            )
    );
-- 6
SELECT *
FROM localities
WHERE locality_id IN (
        SELECT f_locality_id
        FROM roads
        WHERE length > 200
        GROUP BY f_locality_id
        HAVING count(s_locality_id) > 1
    );
-- 7
SELECT *
FROM localities as l1
WHERE locality_id IN (
        SELECT f_locality_id
        FROM roads as r1
        WHERE l1.locality_id <> r1.s_locality_id
            and length > 200
        GROUP BY f_locality_id
        HAVING count(s_locality_id) > 1
    );