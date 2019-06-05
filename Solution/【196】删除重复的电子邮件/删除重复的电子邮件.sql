# 删除的时候， DELETE 是代替了 SELECT 的位置，但是 DELETE 后不跟相应的列名
# NOT IN 
# MIN(id) AS id 这个语法注意下
# 如果最后没有 AS M 就会报错，记住以后弄个子查询要用 AS 


DELETE
FROM
    Person
WHERE
    id NOT IN (SELECT id FROM (SELECT MIN(id) AS id FROM Person GROUP BY email) AS M);