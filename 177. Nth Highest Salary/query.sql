SELECT DISTINCT
    e.salary
FROM Employee e
WHERE
    N = (
        SELECT COUNT(DISTINCT e2.salary)
        FROM Employee e2
        WHERE
            e2.salary >= e.salary
    );

DECLARE offset_val INT;

SET
    offset_val = N - 1
SELECT DISTINCT
    e.salary
FROM Employee e
ORDER BY e.salary DESC
LIMIT offset_val, 1;