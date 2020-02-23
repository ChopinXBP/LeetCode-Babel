package Problems;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Write a program to count the number of days between two dates.
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 * 请你编写一个程序来计算两个日期之间隔了多少天。
 * 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
 *
 */

public class NumberOfDaysBetweenTwoDates {

    public int daysBetweenDates(String date1, String date2) {
        int days1 = count(date1.split("-"));
        int days2 = count(date2.split("-"));
        return Math.abs(days1 - days2);
    }

    public int count(String[] date){
        int result = Integer.parseInt(date[2]);
        int year = Integer.parseInt(date[0]);
        result += daysOfMonth(Integer.parseInt(date[1]), year);
        result += daysOfYear(year);
        return result;
    }

    public boolean isSpecial(int year){
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public int daysOfMonth(int month, int year){
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int count = 0;
        int result = 0;
        while(count < month - 1) {
            result += days[count++];
        }
        return month > 2 && isSpecial(year) ? result + 1 : result;
    }

    public int daysOfYear(int year){
        int result = 0;
        for (int i = 1970; i < year; i++) {
            result += isSpecial(i) ? 366 : 365;
        }
        return result;
    }

    public int daysBetweenDates2(String date1, String date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        try {
            Date start = simpleDateFormat.parse(date1);
            Date end = simpleDateFormat.parse(date2);
            return (int)(end.getTime() - start.getTime());
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
