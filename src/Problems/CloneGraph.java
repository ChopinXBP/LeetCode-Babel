package Problems;

import java.util.*;

/**
 *
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 *
 */

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {}
        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node , new Node(node.val, new ArrayList<>()));
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node curNode = queue.pollFirst();
                for(Node n : curNode.neighbors){
                    if(!map.containsKey(n)){
                        queue.add(n);
                        map.put(n, new Node(n.val, new ArrayList<>()));
                    }
                }
            }
        }

        for(Map.Entry<Node, Node> entry : map.entrySet()){
            for(Node n : entry.getKey().neighbors){
                entry.getValue().neighbors.add(map.get(n));
            }
        }

        return map.get(node);
    }
}
