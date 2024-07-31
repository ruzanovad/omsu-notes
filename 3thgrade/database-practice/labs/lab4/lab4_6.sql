CREATE TABLE IF NOT EXISTS m_data (
    a INT,
    b INT
);
CREATE OR REPLACE FUNCTION a_mono(num_records INT, left_b INT, right_b INT)
RETURNS BOOLEAN AS $$
DECLARE
    r RECORD;
    avg_a DOUBLE PRECISION;
    stddev_a DOUBLE PRECISION;
    median_a DOUBLE PRECISION;
    avg_b DOUBLE PRECISION;
    stddev_b DOUBLE PRECISION;
    median_b DOUBLE PRECISION;
    is_more BOOLEAN;
    first_iter BOOLEAN := TRUE;
    is_mono BOOLEAN := TRUE;
    my_cursor CURSOR FOR SELECT * FROM m_data ORDER BY a;
    r1 RECORD;
    r2 RECORD;
BEGIN
    TRUNCATE TABLE m_data RESTART IDENTITY;

    INSERT INTO m_data (a, b)
    SELECT floor(random() * (right_b - left_b + 1) + left_b)::int, floor(random() * (right_b - left_b + 1) + left_b)::int::int
    FROM generate_series(1, num_records);

    RAISE NOTICE 'm_data';
    FOR r IN SELECT * FROM m_data ORDER BY a LOOP
        RAISE NOTICE '%', to_json(r);
    END LOOP;

    SELECT AVG(a) INTO avg_a FROM m_data;
    SELECT STDDEV(a) INTO stddev_a FROM m_data;
    SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY a) INTO median_a FROM m_data;

    SELECT AVG(b) INTO avg_b FROM m_data;
    SELECT STDDEV(b) INTO stddev_b FROM m_data;
    SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY b) INTO median_b FROM m_data;

    RAISE NOTICE 'AVG(a) = %', to_json(avg_a);
    RAISE NOTICE 'STDDEV(a) = %', to_json(stddev_a);
    RAISE NOTICE 'MEDIAN(a) = %', to_json(median_a);

    RAISE NOTICE 'AVG(b) = %', to_json(avg_b);
    RAISE NOTICE 'STDDEV(b) = %', to_json(stddev_b);
    RAISE NOTICE 'MEDIAN(b) = %', to_json(median_b);

    OPEN my_cursor;
    FETCH my_cursor INTO r1;
    LOOP
        FETCH my_cursor INTO r2;
        EXIT WHEN NOT FOUND;
        IF first_iter THEN
            first_iter := FALSE;
            is_more := r1.a < r2.a;
        ELSE
            IF (is_more and r1.a > r2.a) or (NOT is_more and r1.a < r2.a) THEN
                is_mono := FALSE;
                EXIT;
            END IF;
        END IF;
        IF (is_more and r1.b > r2.b) or (NOT is_more and r1.b < r2.b) THEN
            is_mono := FALSE;
            EXIT;
        END IF;
        r1 := r2;
    END LOOP;
    CLOSE my_cursor;

    RETURN is_mono;
END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
select * from a_mono(10, -100, 100);
