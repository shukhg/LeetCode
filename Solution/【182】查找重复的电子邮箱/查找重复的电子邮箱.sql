# 记住如果要对统计的结果进行过滤，要先用 GROUP BY 再用 HAVING + 统计函数

SELECT
    Email
FROM
    Person
GROUP BY
    Email
HAVING
    COUNT(*) >= 2;