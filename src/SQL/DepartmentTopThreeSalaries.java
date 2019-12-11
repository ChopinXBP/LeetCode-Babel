package SQL;

/**
 *
 * https://leetcode-cn.com/problems/department-top-three-salaries/
 *
 */

public class DepartmentTopThreeSalaries {
}

/*

#每个部门前三高的薪水说明该部门不超过三个人比这一薪水值大
SELECT e1.Salary
FROM Employee e1
WHERE 3 > (
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee e2
    WHERE e1.Salary < e2.Salary AND e1.DepartmentId = e2.DepartmentId
);

#再将该查询得到的薪水与原表连接即可
SELECT d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM Employee e1
INNER JOIN Department d
ON e1.DepartmentId = d.Id
WHERE 3 > (
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee e2
    WHERE e2.Salary > e1.Salary AND e1.DepartmentId = e2.DepartmentId
);

#自定义变量法
SELECT dep.Name Department, emp.Name Employee, emp.Salary
FROM (## 自定义变量RANK, 查找出 每个部门工资前三的排名
        SELECT te.DepartmentId, te.Salary,
               CASE
                    WHEN @pre = DepartmentId THEN @rank:= @rank + 1
                    WHEN @pre := DepartmentId THEN @rank:= 1
               END AS RANK
        FROM (SELECT @pre:=null, @rank:=0)tt,
             (## (部门,薪水)去重,根据 部门(升),薪水(降) 排序
                 SELECT DepartmentId,Salary
                 FROM Employee
                 GROUP BY DepartmentId,Salary
                 ORDER BY DepartmentId,Salary DESC
             )te
       )t
INNER JOIN Department dep ON t.DepartmentId = dep.Id
INNER JOIN Employee emp ON t.DepartmentId = emp.DepartmentId and t.Salary = emp.Salary and t.RANK <= 3
ORDER BY t.DepartmentId, t.Salary DESC ## t 结果集已有序,根据该集合排序

 */