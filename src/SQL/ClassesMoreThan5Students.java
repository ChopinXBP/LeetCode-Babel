package SQL;

/**
 *
 * https://leetcode-cn.com/problems/classes-more-than-5-students/
 *
 */

public class ClassesMoreThan5Students {
}

/*

SELECT c.class AS class
FROM (
    SELECT DISTINCT *
    FROM courses
) c
GROUP BY c.class
HAVING COUNT(*) > 4;

SELECT class
FROM courses
GROUP BY class
HAVING COUNT(DISTINCT student) > 4;

 */