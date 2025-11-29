--QUESTION 1


--OPTION 1
SELECT DISTINCT(salary) FROM emp ORDER BY salary DESC LIMIT 1 OFFSET 1;
--OPTION 2
SELECT MAX(salary) FROM emp WHERE salary < (SELECT MAX(salary) FROM emp);
--OPTION 3
SELECT salary FROM (SELECT DISTINCT salary FROM emp ORDER BY salary DESC LIMIT 2) AS emp ORDER BY salary LIMIT 1;


--QUESTION 2

SELECT g.yr, g.city, c.country
FROM games g
LEFT JOIN city c
ON g.city = c.name;


--QUESTION 3

--Left Join statement
--Returns all rows from the left table (games) and the matching rows from the right table (city).
--If there is no match, the right table columns will show NULL.

SELECT g.yr, g.city, c.country
FROM games g
LEFT JOIN city c
ON g.city = c.name;

--Returns all rows from the right table (city) and the matching rows from the left table (games).
--If there is no match, the left table columns will show NULL.

SELECT g.yr, g.city, c.country
FROM games g
RIGHT JOIN city c
ON g.city = c.name;


--QUESTION 4

SELECT userId, AVG(duration) AS AverageDuration
FROM sessions
GROUP BY userId
HAVING COUNT(*) > 1;
