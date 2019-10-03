import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 * Note: The number of people is less than 1,100.
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
 * 编写一个算法来重建这个队列。
 * 注意：总人数少于1100人。
 *
 */

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> b[0] - a[0] == 0 ? a[1] - b[1] : b[0] - a[0]);
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] pp : people){
            list.add(pp[1], pp);
        }
        return list.toArray(new int[0][]);
    }
}
