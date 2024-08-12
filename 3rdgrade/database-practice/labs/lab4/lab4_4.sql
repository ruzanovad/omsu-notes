CREATE OR REPLACE FUNCTION f_analyze(num_records INT)
RETURNS TABLE (
    dataset TEXT,
    mean DOUBLE PRECISION,
    stddev DOUBLE PRECISION,
    median DOUBLE PRECISION
) AS $$
DECLARE
    avg_a DOUBLE PRECISION;
    min_a INT;
    max_a INT;
    r RECORD;
BEGIN
    CREATE TEMP TABLE IF NOT EXISTS data (
        id SERIAL PRIMARY KEY,
        a INT
    );
    TRUNCATE TABLE data RESTART IDENTITY;

    INSERT INTO data (a)
    SELECT floor(random() * 100 + 1)::int
    FROM generate_series(1, num_records);

    SELECT AVG(a) INTO avg_a FROM data;

    SELECT MIN(a), MAX(a) INTO min_a, max_a FROM data;

    CREATE TEMP TABLE IF NOT EXISTS data_prime (
        id_prime INT,
        a_prime INT
    );
    TRUNCATE TABLE data_prime RESTART IDENTITY;

    FOR r IN SELECT * FROM data LOOP
        INSERT INTO data_prime(id_prime, a_prime)
        VALUES (
            r.id,
            CASE 
               WHEN r.a > avg_a THEN min_a
               ELSE max_a
            END
        );
    END LOOP;

    RAISE NOTICE 'DATA';
    FOR r IN SELECT * FROM data LOOP
        RAISE NOTICE '%', to_json(r);
    END LOOP;
    RAISE NOTICE 'DATA_PRIME';
    FOR r IN SELECT * FROM data_prime LOOP
        RAISE NOTICE '%', to_json(r);
    END LOOP;

    RETURN QUERY
    WITH stats AS (
        SELECT 
            'data' AS dataset,
            AVG(a)::double precision AS mean,
            STDDEV(a)::double precision AS stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY a)::double precision AS median
        FROM data
    ),
    stats_prime AS (
        SELECT 
            'data_prime' AS dataset,
            AVG(a_prime)::double precision AS mean,
            STDDEV(a_prime)::double precision AS stddev,
            PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY a_prime)::double precision AS median
        FROM data_prime
    )
    SELECT * FROM stats
    UNION ALL
    SELECT * FROM stats_prime;

END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
select * from f_analyze(10);
