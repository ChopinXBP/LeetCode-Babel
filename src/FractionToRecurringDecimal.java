import java.util.HashMap;

/**
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 */

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0){
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        if(numerator < 0 ^ denominator < 0){
            fraction.append("-");
        }

        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;

        //如果余数为0，直接返回
        if(remainder == 0){
            return fraction.toString();
        }
        fraction.append(".");

        //模拟小学长除法公式，用哈希表记录余数出现在小数部分的位置
        HashMap<Long, Integer> map = new HashMap<>();
        while(remainder != 0){
            //当发现已经出现的余数，就可以将重复出现的小数部分用括号括起来。
            if(map.containsKey(remainder)){
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }

        return fraction.toString();
    }
}
