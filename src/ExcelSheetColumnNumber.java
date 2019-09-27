/**
 *
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 */

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int idx = s.length() - 1;
        int result = 0;
        int loc = 0;
        while(idx >= 0){
            result += (s.charAt(idx--) - 'A' + 1) * Math.pow(26, loc++);
        }
        return result;
    }
}
