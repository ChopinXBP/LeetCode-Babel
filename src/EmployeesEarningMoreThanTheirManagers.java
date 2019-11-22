/**
 *
 * https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/
 *
 */

public class EmployeesEarningMoreThanTheirManagers {
}

/*

SELECT e1.Name 'Employee'
FROM Employee e1
INNER JOIN Employee e2
ON e1.ManagerId = e2.Id
WHERE e1.Salary > e2.Salary;

SELECT e1.Name 'Employee'
FROM Employee e1, Employee e2
WHERE e1.ManagerId = e2.Id AND e1.Salary > e2.Salary;

 */