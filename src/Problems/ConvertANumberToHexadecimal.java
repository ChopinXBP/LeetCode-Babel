package Problems;

/**
 *
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 *
 */

public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        String[] match = {"a", "b", "c", "d", "e", "f"};
        String result = "";
        boolean high = true;
        int pattern = 0xf0000000;
        int count = 28;
        while(pattern != 0){
            int cur = num & pattern;
            cur >>>= count;
            count -= 4;
            pattern >>>= 4;
            if(high){
                if(cur == 0){
                    continue;
                }
                high = false;
            }
            if(cur < 10){
                result = result + cur;
            }else{
                result = result + match[cur - 10];
            }
        }
        return result;
    }
}
