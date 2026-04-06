SELECT (
        SELECT DISTINCT
            e.salary
        FROM Employee e
        WHERE
            1 = (
                SELECT COUNT(DISTINCT e2.salary)
                FROM Employee e2
                WHERE
                    e2.salary > e.salary
            )
    ) as SecondHighestSalary;

SELECT (
        SELECT DISTINCT
            e.salary
        FROM Employee e
        ORDER BY salary DESC
        LIMIT 1
        OFFSET
            1
    ) as SecondHighestSalary;

SELECT MAX(e.salary) as SecondHighestSalary
FROM Employee e
WHERE
    e.salary < (
        SELECT MAX(e2.salary)
        FROM Employee e2
    );