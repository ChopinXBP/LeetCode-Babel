package SQL;

/**
 *
 * https://leetcode-cn.com/problems/rising-temperature/
 *
 */

public class RisingTemperature {
}

/*

SELECT w1.Id
FROM weather w1
INNER JOIN weather w2
ON DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 AND w1.Temperature > w2.Temperature;


 */