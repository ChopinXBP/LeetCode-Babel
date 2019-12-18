package SQL;

/**
 *
 * https://leetcode-cn.com/problems/trips-and-users/
 *
 */

public class TripsAndUsers {
}

/*

#先排除被禁止的用户，再筛选被取消的订单进行计算
#COUNT(条件表达式 OR NULL)可以计算符合条件的行数

SELECT v.Day, ROUND(COUNT(v.status != 'completed' OR NULL) / COUNT(*), 2) AS 'Cancellation Rate'
FROM (
    SELECT t.Status AS 'status', t.Request_at AS 'Day'
    FROM Trips t
    WHERE t.Request_at BETWEEN '2013-10-01' AND '2013-10-03'
        AND t.Client_Id NOT IN( SELECT Users_Id FROM Users WHERE Banned = 'YES' AND Role = 'client')
        AND t.Driver_Id NOT IN( SELECT Users_Id FROM Users WHERE Banned = 'YES' AND Role = 'driver')
) v
GROUP BY v.Day;

 */