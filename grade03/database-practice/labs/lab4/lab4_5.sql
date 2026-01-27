CREATE OR REPLACE FUNCTION f_analyze2(num_records INT)
RETURNS TABLE (
    dataset TEXT,
    a_mean DOUBLE PRECISION,
    a_stddev DOUBLE PRECISION,
    a_median DOUBLE PRECISION,
    b_mean DOUBLE PRECISION,
    b_stddev DOUBLE PRECISION,
    b_median DOUBLE PRECISION
) AS $$
DECLARE
    r RECORD;
BEGIN
    CREATE TEMP TABLE IF NOT EXISTS data2 (
        id SERIAL PRIMARY KEY,
        a INT,
        b DOUBLE PRECISION
    );
    TRUNCATE TABLE data2 RESTART IDENTITY;

    INSERT INTO data2 (a)
    SELECT floor(random() * 100 + 1)::int
    FROM generate_series(1, num_records);

    FOR r IN SELECT * FROM data2 LOOP
        UPDATE data2 set b = (0.01 * r.a) WHERE r.id = id;
    END LOOP;

    CREATE TEMP TABLE IF NOT EXISTS data2_prime (
        id_prime INT,
        a_prime INT,
        b_prime DOUBLE PRECISION
    );
    TRUNCATE TABLE data2_prime RESTART IDENTITY;

    FOR r IN SELECT * FROM data2 LOOP
        INSERT INTO data2_prime(id_prime, a_prime, b_prime)
        VALUES (
            r.id,
            r.a,
            r.a * r.a
        );
    END LOOP;

    RAISE NOTICE 'DATA';
    FOR r IN SELECT * FROM data2 LOOP
        RAISE NOTICE '%', to_json(r);
    END LOOP;
    RAISE NOTICE 'DATA_PRIME';
    FOR r IN SELECT * FROM data2_prime LOOP
        RAISE NOTICE '%', to_json(r);
    END LOOP;

    RETURN QUERY
    WITH stats AS (
        SELECT 
            'data2' AS dataset,
            AVG(a)::double precision AS a_mean,
            STDDEV(a)::double precision AS a_stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY a)::double precision AS a_median,
            AVG(b)::double precision AS b_mean,
            STDDEV(b)::double precision AS b_stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY b)::double precision AS b_median
        FROM data2
    ),
    stats_prime AS (
        SELECT 
            'data2_prime' AS dataset,
            AVG(a_prime)::double precision AS a_mean,
            STDDEV(a_prime)::double precision AS a_stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY a_prime)::double precision AS a_median,
            AVG(b_prime)::double precision AS b_mean,
            STDDEV(b_prime)::double precision AS b_stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY b_prime)::double precision AS b_median
        FROM data2_prime
    )
    SELECT * FROM stats
    UNION ALL
    SELECT * FROM stats_prime;

END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
select * from f_analyze2(10);
