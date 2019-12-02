package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 */

public class RestoreIPAddresses {
    //动态规划
    public List<String> restoreIpAddresses(String s) {
        if(s.length() > 12 || s.length() < 4){
            return new ArrayList<>();
        }
        ArrayList<ArrayList<String>>[] dp = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<ArrayList<String>>(){{add(new ArrayList<>());}};

        for(int i = 1; i <= s.length(); i++){
            dp[i] = new ArrayList<>();
            for(int j = i - 3 > 0 ? i - 3 : 0; j < i; j++){
                String str = s.substring(j, i);
                int num = Integer.valueOf(str);
                if((str.charAt(0) == '0' && str.length() != 1) || num > 255){
                    continue;
                }
                ArrayList<ArrayList<String>> pre = dp[j];
                for(ArrayList<String> prestr : pre){
                    if(prestr.size() > 3){
                        continue;
                    }
                    dp[i].add(new ArrayList<String>(prestr){{add(str);}});
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for(ArrayList<String> strs : dp[s.length()]){
            if(strs.size() != 4){
                continue;
            }
            result.add(strs.get(0) + "." + strs.get(1) + "." + strs.get(2) + "." + strs.get(3));
        }
        return result;
    }

    //递归
    private void restoreIpAddresses(String s, ArrayList<String> result, ArrayList<String> ip, int start) {
        if (start == s.length() && ip.size() == 4) {
            result.add(ip.get(0) + "." + ip.get(1) + "." + ip.get(2) + "." + ip.get(3));
            return;
        }
        if (s.length() - start > 3 * (4 - ip.size())){
            return;
        }
        if (s.length() - start < 4 - ip.size()){
            return;
        }
        int num = 0;
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {
                break;
            }
            if (i > start && s.charAt(start) == '0'){
                break;
            }
            ip.add(s.substring(start, i + 1));
            restoreIpAddresses(s, result, ip, i + 1);
            ip.remove(ip.size() - 1);
        }
    }

    public ArrayList<String> restoreIpAddresses2(String s) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> ip = new ArrayList<>();
        if (s == null){
            return result;
        }
        restoreIpAddresses(s, result, ip, 0);
        return result;
    }
}
