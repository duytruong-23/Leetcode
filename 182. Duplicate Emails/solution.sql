CREATE TABLE Person (
    id INT PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);

SELECT distinct p.email as Email
FROM Person p
WHERE p.email IS NOT NULL AND EXISTS (
    SELECT 1
    FROM Person p2
    where p2.email = p.email AND p2.id <> p.id
);


SELECT distinct p.email as Email
FROM Person p
WHERE p.email IS NOT NULL AND p.email IN (
    SELECT p2.email
    FROM Person p2
    where p2.id <> p.id
);

SELECT distinct p.email as Email
FROM Person p
GROUP BY p.email
HAVING COUNT(p.email) > 1