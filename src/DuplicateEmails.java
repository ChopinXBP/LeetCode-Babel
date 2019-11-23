/**
 *
 * https://leetcode-cn.com/problems/duplicate-emails/
 *
 */

public class DuplicateEmails {
}

/*

SELECT a.Email
FROM (
    SELECT Email, COUNT(Email) 'num'
    FROM Person
    GROUP BY Email
) a
WHERE a.num > 1;

select Email
from Person
group by Email
having count(Email) > 1;

 */