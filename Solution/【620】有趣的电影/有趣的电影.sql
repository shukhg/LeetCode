# 注意要写得有层次感
# INT 类型的数据可以直接 % 2  但是要注意是 =  而不是  ==
# 可以直接  = 或者 ！=  字符串   而不是用 IS NOT
# 最好是用 <>  而不用 !=
# DESC 加在最后



SELECT
    *
FROM
    cinema
WHERE
    id % 2 = 1
    AND description <> 'boring'
ORDER BY
    rating 
    DESC;