package Problems;

import java.util.LinkedList;

/**
 *
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature.
 * For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1,
 * then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number
 * of friend circles among all the students.
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，
 * 那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，
 * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 */

public class FriendCircles {
    public int findCircleNum(int[][] M) {
        boolean[] isVisited = new boolean[M.length];
        LinkedList<Integer> queue = new LinkedList<>();
        int result = 0;
        for(int i = 0; i < M.length; i++){
            if(isVisited[i]){
                continue;
            }
            queue.add(i);
            isVisited[i] = true;
            result++;
            while(!queue.isEmpty()){
                int size = queue.size();
                for(int j = 0; j < size; j++){
                    int curNum = queue.pollFirst();
                    for(int k = 0; k < M.length; k++){
                        if(isVisited[k] || M[curNum][k] == 0){
                            continue;
                        }
                        isVisited[k] = true;
                        queue.add(k);
                    }
                }
            }
        }
        return result;
    }
}
