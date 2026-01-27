DROP FUNCTION IF EXISTS fllt_streak;
CREATE FUNCTION fllt_streak(needed_lt INT)
RETURNS TABLE (
    locality_type_id INT,
    longest_streak INT
) AS $$
DECLARE
    rec RECORD;
    streak INT := 0;
    max_streak INT := 0;
    my_cursor CURSOR FOR
        SELECT l1.locality_type_id
        FROM localities as l1
        ORDER BY l1.locality_id;
BEGIN
    open my_cursor;

    fetch my_cursor into rec;

    while found loop
        if (rec.locality_type_id = needed_lt) then
            streak := streak + 1;
        else if (rec.locality_type_id <> needed_lt) then
            if (streak > max_streak) then
                max_streak := streak;
                streak := 0;
            else
                streak := 0;
            end if;
        end if;
        end if;
        fetch my_cursor into rec;
    end loop;
    if (streak > max_streak) then
        max_streak := streak;
    end if;

    close my_cursor;
    RETURN QUERY VALUES(needed_lt, max_streak);
    end;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
SELECT * FROM fllt_streak(2);
