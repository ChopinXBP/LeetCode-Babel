package SQL;

/**
 *
 * https://leetcode-cn.com/problems/exchange-seats/
 *
 */

public class ExchangeSeats {
}

/*

SELECT s1.id, IFNULL(s2.student, s1.student) AS 'student'
FROM seat s1
LEFT JOIN seat s2
ON s1.id = IF((s2.id & 1) = 0, s2.id - 1, s2.id + 1)
ORDER BY s1.id;


 */