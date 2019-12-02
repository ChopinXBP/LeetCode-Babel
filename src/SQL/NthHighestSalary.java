package SQL;

/**
 *
 * https://leetcode-cn.com/problems/nth-highest-salary/
 *
 */

public class NthHighestSalary {
}

/*

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  #不允许在LIMIT处运算
  SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT IFNULL(
            (SELECT DISTINCT Salary
            FROM Employee
            ORDER BY Salary DESC
            LIMIT N, 1)
      , NULL)
  );
END

 */