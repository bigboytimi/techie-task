--Step 1: Left Join statement
--Returns all rows from the left table (games) and the matching rows from the right table (city).
--If there is no match, the right table columns will show NULL.

SELECT g.yr, g.city, c.country
FROM games g
LEFT JOIN city c
ON g.city = c.name;


--Step 2: Right Join statement
--Returns all rows from the right table (city) and the matching rows from the left table (games).
--If there is no match, the left table columns will show NULL.

SELECT g.yr, g.city, c.country
FROM games g
RIGHT JOIN city c
ON g.city = c.name;
