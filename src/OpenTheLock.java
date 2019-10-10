import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 */

public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        HashSet<String> isVisited = new HashSet<>();
        for(String s : deadends){
            isVisited.add(s);
        }
        if(isVisited.contains("0000")){
            return -1;
        }
        LinkedList<String> queue = new LinkedList<>();
        queue.add("0000");
        int result = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String s = queue.pollFirst();
                if(s.equals(target)){
                    return result;
                }
                ArrayList<String> nextStr = Solution(s);
                for(String next : nextStr){
                    if(!isVisited.contains(next)){
                        queue.add(next);
                        isVisited.add(next);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public ArrayList<String> Solution(String str){
        ArrayList<String> result = new ArrayList<>();
        char[] bytes = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            if(bytes[i] == '0'){
                bytes[i] = '9';
                result.add(new String(bytes));
                bytes[i] = '1';
                result.add(new String(bytes));
                bytes[i]--;
            }else if(bytes[i] == '9'){
                bytes[i] = '0';
                result.add(new String(bytes));
                bytes[i] = '8';
                result.add(new String(bytes));
                bytes[i]++;
            }else{
                bytes[i]++;
                result.add(new String(bytes));
                bytes[i] -= 2;
                result.add(new String(bytes));
                bytes[i]++;
            }
        }
        return result;
    }
}
