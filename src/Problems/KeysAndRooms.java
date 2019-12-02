package Problems;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
 * A key rooms[i][j] = v opens the room with number v.
 * Initially, all the rooms start locked (except for room 0).
 * You can walk back and forth between rooms freely.
 * Return true if and only if you can enter every room.
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。
 * 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 */

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        LinkedList<List<Integer>> queue = new LinkedList<>();
        queue.add(rooms.get(0));
        boolean[] enter = new boolean[rooms.size()];
        enter[0] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                List<Integer> keys = queue.pollFirst();
                for(int key : keys){
                    if(!enter[key]){
                        queue.add(rooms.get(key));
                        enter[key] = true;
                    }
                }
            }
        }
        for(boolean room : enter){
            if(!room){
                return false;
            }
        }
        return true;
    }
}
