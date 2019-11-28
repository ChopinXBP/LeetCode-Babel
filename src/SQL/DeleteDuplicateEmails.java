package SQL;

/**
 *
 * https://leetcode-cn.com/problems/delete-duplicate-emails/
 *
 */

public class DeleteDuplicateEmails {
}

/*

DELETE p1
FROM Person p1, Person p2
WHERE p1.email = p2.email AND p1.Id > p2.Id;

#MySQL不允许select和delete操作同一个表，会出现以下错误
#错误：You can't specify target table 'person' for update in FROM clause
#解决办法：添加一个临时表
DELETE FROM Person
WHERE Id NOT IN(
    SELECT Id
    FROM(
        SELECT MIN(Id) AS id
        FROM Person
        GROUP BY Email
    ) temp
);


 */