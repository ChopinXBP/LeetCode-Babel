package SQL;

/**
 *
 * https://leetcode-cn.com/problems/second-highest-salary/
 *
 */

public class SecondHighestSalary {
}

/*

SELECT IFNULL(
    (SELECT DISTINCT(Salary)
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1, 1)
, NULL) 'SQL.SecondHighestSalary';

 */
