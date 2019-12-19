package SQL;

/**
 *
 * https://leetcode-cn.com/problems/human-traffic-of-stadium/
 *
 */

public class HumanTrafficOfStadium {
}

/*

SELECT DISTINCT s1.id, s1.visit_date, s1.people
FROM stadium s1, stadium s2, stadium s3
WHERE s1.people >= 100 AND s2.people >= 100 AND s3.people >= 100 AND (
    ((s1.id = s2.id - 1) AND (s1.id = s3.id - 2))
    OR ((s1.id = s2.id - 1) AND (s1.id = s3.id + 1))
    OR ((s1.id = s2.id + 1) AND (s1.id = s3.id + 2))
)
ORDER BY s1.id;


SELECT id, visit_date, people
FROM (
	SELECT r1.*, @flag := if((r1.countt >= 3 OR @flag = 1) AND r1.countt != 0, 1, 0) AS flag
	FROM (
		SELECT s.*, @count := if(s.people >= 100, @count + 1, 0) AS `countt`
		FROM stadium s, (SELECT @count := 0) b
	) r1, (SELECT @flag := 0) c
	ORDER BY id DESC
) result
WHERE flag = 1 ORDER BY id;

 */