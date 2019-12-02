package Problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers,
 * output all of them with no order requirement. You could assume there always exists an answer.
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 *
 */

public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        for(int i = 0; i < list1.length; i++){
            map1.put(list1[i], i);
        }
        HashMap<String, Integer> map2 = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list2.length; i++){
            if(map1.containsKey(list2[i])){
                int idx = map1.get(list2[i]) + i;
                map2.put(list2[i], idx);
                min = idx < min ? idx : min;
            }
        }
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map2.entrySet()){
            if(entry.getValue() == min){
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[0]);
    }
}
