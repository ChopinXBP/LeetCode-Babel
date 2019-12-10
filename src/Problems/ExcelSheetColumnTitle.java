package Problems;

/**
 *
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 */

public class ExcelSheetColumnTitle {
    //移位进制转换
    //xi*26^(i-1) + … + x3*26^2 + x2*26^1 + x1*26^0 = n
    //每次num=n%26可以取出最低位的xi=num，n/=26可以去掉最低位的xi
    //但题目每个位的范围是1-26，而不是0-25。因此当num==26时需要进行修正，将n-1是使n不满足26的倍数，再除26即可消去最底的xi
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while(n > 0){
            int num = n % 26;
            if(num == 0){
                num = 26;
                n--;
            }
            result.insert(0, (char)('A' + num - 1));
            n /= 26;
        }
        return result.toString();
    }
}
