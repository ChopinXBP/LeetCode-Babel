package SQL;

/**
 *
 * https://leetcode-cn.com/problems/customers-who-never-order/
 *
 */

public class CustomersWhoNeverOrder {
}

/*

SELECT Name AS 'Customers'
FROM Customers c
LEFT OUTER JOIN (
    SELECT DISTINCT CustomerID AS 'cId'
    FROM Orders
) o
ON c.Id = o.cId
WHERE cId IS NULL;

SELECT Name AS 'Customers'
FROM Customers c
WHERE c.Id NOT IN (
    SELECT DISTINCT CustomerID AS 'cId'
    FROM Orders
);

SELECT c.Name AS 'Customers'
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.CustomerID = c.Id
);

 */