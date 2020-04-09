package Problems;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        StringBuilder result = new StringBuilder();
        int times = (numRows << 1) - 2;
        for (int i = 0; i < s.length(); i += times) {
            result.append(s.charAt(i));
        }
        for (int i = 1; i < numRows - 1; i++) {
            int cut = times - i - i;
            for (int j = i; j < s.length(); j += times) {
                result.append(s.charAt(j));
                if(j + cut < s.length()){
                    result.append(s.charAt(j + cut));
                }
            }
        }
        for (int i = numRows - 1; i < s.length(); i += times) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
