# 记住连接的时候要对表起个别名，不然会报错


SELECT
    FirstName,
    LastName,
    City,
    State
FROM
    Person p
    LEFT JOIN Address a 
    ON p.PersonId = a.PersonId;