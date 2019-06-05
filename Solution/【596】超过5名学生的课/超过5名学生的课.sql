# 先使用 GROUP BY 进行聚合，然后再使用 HAVING 对聚合的结果进行过滤
# HAVING 后面是可以跟统计函数的，一般要用统计函数都要有 GROUP BY 


SELECT
    class
FROM
    courses
GROUP BY
    class
HAVING
    COUNT( DISTINCT student) >= 5;