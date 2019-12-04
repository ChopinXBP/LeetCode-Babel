package SQL;

/**
 *
 * https://leetcode-cn.com/problems/consecutive-numbers/
 *
 */

public class ConsecutiveNumbers {
}

/*

SELECT DISTINCT l1.Num AS 'ConsecutiveNums'
FROM Logs l1, Logs l2, Logs l3
WHERE l1.Id = l2.Id - 1
    AND l1.Id = l3.Id -2
    AND l1.Num = l2.Num
    AND l1.Num = l3.Num;

SELECT DISTINCT Num AS ConsecutiveNums
FROM (
  SELECT Num,
    CASE
      WHEN @prev = Num THEN @count := @count + 1
      WHEN (@prev := Num) IS NOT NULL THEN @count := 1
    END AS CNT
  FROM Logs, (SELECT @prev := null,@count := null) AS t
) AS temp
WHERE temp.CNT >= 3

 */