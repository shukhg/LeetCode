# 表的在最下面，是要查找第一列中出现而且也在第四列中出现的 Id，这种时候就要想到 JOIN
# 以后注意这种表的结构，可以使用表自身进行连接（一般是 INNER JOIN）
# 既然是 JOIN ， 就一定要有 AS 进行重命名（表的时候可以省略 AS 关键字）


SELECT
    E1.Name AS  Employee
FROM
    Employee E1
    INNER JOIN Employee E2
    ON ManagerId = E2.Id
    AND E1.Salary > E2.Salary;



# 这是表的结构
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, ManagerId int)
Truncate table Employee
insert into Employee (Id, Name, Salary, ManagerId) values ('1', 'Joe', '70000', '3')
insert into Employee (Id, Name, Salary, ManagerId) values ('2', 'Henry', '80000', '4')
insert into Employee (Id, Name, Salary, ManagerId) values ('3', 'Sam', '60000', 'None')
insert into Employee (Id, Name, Salary, ManagerId) values ('4', 'Max', '90000', 'None')