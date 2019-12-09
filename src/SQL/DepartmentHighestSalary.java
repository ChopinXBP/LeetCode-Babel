package SQL;

/**
 *
 * https://leetcode-cn.com/problems/department-highest-salary/
 *
 */

public class DepartmentHighestSalary {
}

/*

#注意：1.最高工资不止一人；2.可能有人的部门号没有对应部门名

#左连接后要判断NULL值
SELECT d.Name AS 'Department', t.Name AS 'Employee', t.Salary AS 'Salary'
FROM (
    SELECT e.Name, e.Salary, e.DepartmentId
    FROM (SELECT DepartmentId, MAX(Salary) AS 'maxSalary'
        FROM Employee
        GROUP BY DepartmentId) m, Employee e
    WHERE e.Salary = m.maxSalary AND e.DepartmentId = m.DepartmentId
) t
LEFT JOIN Department d
ON t.DepartmentId = d.Id
WHERE d.Name IS NOT NULL;

#内连接会自动去除NULL值
SELECT d.Name AS 'Department', t.Name AS 'Employee', t.Salary AS 'Salary'
FROM (
    SELECT e.Name, e.Salary, e.DepartmentId
    FROM (SELECT DepartmentId, MAX(Salary) AS 'maxSalary'
        FROM Employee
        GROUP BY DepartmentId) m, Employee e
    WHERE e.Salary = m.maxSalary AND e.DepartmentId = m.DepartmentId
) t
INNER JOIN Department d
ON t.DepartmentId = d.Id;

#IN左右可以连接多个值对
SELECT d.Name AS 'Department', e.Name AS 'Employee', e.Salary AS 'Salary'
FROM Employee e
INNER JOIN Department d
ON e.DepartmentId = d.Id
WHERE (e.DepartmentId, e.Salary) IN (
    SELECT DepartmentId, MAX(Salary)
    FROM Employee
    GROUP BY DepartmentId
);

 */