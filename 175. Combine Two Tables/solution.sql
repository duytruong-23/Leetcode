CREATE TABLE Person (
    personId INT PRIMARY KEY,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL
);

CREATE TABLE Address (
    addressId INT PRIMARY KEY,
    personId INT,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    FOREIGN KEY (personId) REFERENCES Person(personId)
);


SELECT p.firstName, p.lastName, a.city, a.state
FROM Person p
LEFT JOIN Address a ON a.personId = p.personId;