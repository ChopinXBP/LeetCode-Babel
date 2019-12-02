package Problems;

import java.util.*;

/**
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 *
 */

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        if(equations.size() == 0){
            Arrays.fill(result, -1.0);
            return result;
        }

        HashSet<String> zeros = new HashSet<>();
        HashMap<String, HashMap<String, Double>> map = new HashMap<>();
        for(int i = 0; i < values.length; i++){
            String up = equations.get(i).get(0);
            if(values[i] > -0.0000001 && values[i] < 0.0000001){
                zeros.add(up);
                continue;
            }
            String down = equations.get(i).get(1);
            map.putIfAbsent(up, new HashMap<>());
            map.get(up).put(down, values[i]);
            map.putIfAbsent(down, new HashMap<>());
            map.get(down).put(up, 1.0 / values[i]);
        }

        for(int i = 0; i < queries.size(); i++){
            String up = queries.get(i).get(0);
            if(zeros.contains(up)){
                result[i] = 0.0;
                continue;
            }
            String down = queries.get(i).get(1);
            if(!map.containsKey(up) || !map.containsKey(down)){
                result[i] = -1.0;
                continue;
            }
            if(map.get(up).containsKey(down)){
                result[i] = map.get(up).get(down);
                continue;
            }
            //记忆BFS
            result[i] = Solution(up, down, map, zeros);
        }

        return result;
    }

    public double Solution(String up, String down, HashMap<String, HashMap<String, Double>> map, HashSet<String> zeros){
        HashSet<String> isVisited = new HashSet<>();
        LinkedList<String> queueStr = new LinkedList<>();
        LinkedList<Double> queueResult = new LinkedList<>();
        queueStr.add(up);
        queueResult.add(1.0);
        while(!queueStr.isEmpty()){
            int size = queueStr.size();
            for(int i = 0; i < size; i++){
                String str = queueStr.pollFirst();
                Double res = queueResult.pollFirst();
                HashMap<String, Double> value = map.get(str);
                for(Map.Entry<String, Double> entry : value.entrySet()){
                    String key = entry.getKey();
                    Double val = res * entry.getValue();
                    if(key.equals(down)){
                        return val;
                    }
                    if(!isVisited.contains(key)){
                        queueStr.add(key);
                        isVisited.add(key);
                        queueResult.add(val);
                        map.get(up).putIfAbsent(key, val);
                    }
                }
            }
        }
        return -1.0;
    }
}
