package Problems;

/**
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 */

public class IntegerToRoman {
    public static void main(String[] args){
        System.out.println(intToRoman(3));
    }

    private static class Roman{
        String str;
        int num;
        Roman(String str, int num){
            this.str = str;
            this.num = num;
        }
    }
    private static Roman[] romanNum = {new Roman("M", 1000), new Roman("CM", 900), new Roman("D", 500), new Roman("CD", 400),
                                new Roman("C", 100), new Roman("XC", 90), new Roman("L", 50), new Roman("XL", 40),
                                new Roman("X", 10), new Roman("IX", 9), new Roman("V", 5), new Roman("IV", 4), new Roman("I", 1),};

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int idx = 0;
        while(num > 0 && idx < 13){
            int times = num / romanNum[idx].num;
            num -= romanNum[idx].num * times;
            while(times-- > 0){
                result.append(romanNum[idx].str);
            }
            idx++;
        }
        return result.toString();
    }

    public static int romanToInt(String s) {
        int result = 0;
        int romanidx = 0;
        int idx = 0;
        int len = s.length();
        while(idx < len && romanidx < 13){
            int wordlen = romanNum[romanidx].str.length();
            while(idx + wordlen <= len && romanNum[romanidx].str.equals(s.substring(idx, idx + wordlen))){
                result += romanNum[romanidx].num;
                idx += wordlen;
            }
            romanidx++;
        }
        return result;
    }

}
