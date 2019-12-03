package SQL;

/**
 *
 * https://leetcode-cn.com/problems/rank-scores/
 *
 */

public class RankScores {
}

/*

#行数 1 2 3 4 5 6
SELECT Score,
    ROW_NUMBER() OVER (ORDER BY Score DESC) AS 'Rank'
FROM Scores;

#排名 1 2 3 3 3 4
SELECT Score,
    DENSE_RANK() OVER (ORDER BY Score DESC) AS 'Rank'
FROM Scores;

#排名 1 2 3 3 3 6
SELECT Score,
    RANK() OVER (ORDER BY Score DESC) AS 'Rank'
FROM Scores;

#取大于等于当前分数的所有分数值，进行去重求数量，即为当前rank
SELECT a.Score,
    (SELECT COUNT(DISTINCT b.Score)
    FROM Scores b
    WHERE b.Score >= a.Score) AS 'Rank'
FROM Scores a
ORDER BY a.Score DESC;

#变量和赋值操作
select  Score,
    ((CASE
    WHEN @prevRank = Score then @curRank
    WHEN @prevRank := Score then @curRank := @curRank + 1
    ELSE @curRank := @curRank +1
    END )*1) AS 'Rank'
FROM Scores,(SELECT @curRank := 0,@prevRank := NULL) p
ORDER BY Score DESC

 */