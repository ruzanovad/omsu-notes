CREATE TABLE IF NOT EXISTS "questions" (
    question text not null,
    answer_1 varchar(32) not null,
    answer_2 varchar(32) not null,
    answer_3 varchar(32) not null,
    difficult int not null
);
INSERT INTO "questions" VALUES
    ('1+1=?', '3', '1', '2', 1),
    ('2^2=?', '4', '8', '16', 1),
    ('Capital of the Russia?', 'Moscow', 'Saint-Petersburg', 'Leningrad', 1),
    ('First prime number?', '1', '2', '3', 2),
    ('sqrt(144)', '12', '-16', '+-12', 2),
    ('ln(1)', '-1', '0', '1', 3),
    ('Count of days in the year?', '365', '31', '365(366)', 1),
    ('10-8=?', '1', '3', '2', 3),
    ('7*3=?', '21', '49', '27', 3),
    ('56/8=?', '9', '-5', '7', 3),
    ('121/11=?', '-11', '0', '11', 2),
    ('4*3=?', '12', '13', '14', 2),
    ('3+3=?', '5', '6', '7', 1);
---------------------------------------------------------------
;
CREATE OR REPLACE FUNCTION shuffle_answers(answer_1 varchar(32), answer_2 varchar(32), answer_3 varchar(32))
RETURNS varchar(32)[] AS $$
DECLARE
    s_cursor CURSOR FOR SELECT * FROM (
        SELECT * FROM (
            SELECT answer_1 UNION
            SELECT answer_2 UNION
            SELECT answer_3
        )
        ORDER BY random()
    );
    answer varchar(32);
    answers varchar(32)[];
BEGIN
    OPEN s_cursor;
    FETCH s_cursor INTO answer;
    answers := array_append(answers, answer);
    FETCH s_cursor INTO answer;
    answers := array_append(answers, answer);
    FETCH s_cursor INTO answer;
    answers := array_append(answers, answer);
    CLOSE s_cursor;
    RETURN answers;
END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
CREATE OR REPLACE FUNCTION random_test()
RETURNS TABLE(question TEXT, answer_1 varchar(32), answer_2 varchar(32), answer_3 varchar(32), difficult INT) AS $$
DECLARE
    r RECORD;
    answers varchar(32)[];
    my_cursor CURSOR FOR SELECT * FROM questions;
BEGIN
    CREATE TEMP TABLE tmp_table (question TEXT, answer_1 varchar(32), answer_2 varchar(32), answer_3 varchar(32), difficult INT) ON COMMIT DROP;
    
    OPEN my_cursor;
    LOOP
        FETCH my_cursor INTO r;
        EXIT WHEN NOT FOUND;
        answers := shuffle_answers(r.answer_1, r.answer_2, r.answer_3);
        INSERT INTO tmp_table VALUES (r.question, answers[1], answers[2], answers[3], r.difficult);
    END LOOP;
    CLOSE my_cursor;

    RETURN QUERY SELECT * FROM tmp_table ORDER BY random();
END;
$$ LANGUAGE plpgsql;
---------------------------------------------------------------
;
select * from random_test();
