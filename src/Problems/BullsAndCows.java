package Problems;

/**
 *
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is.
 * Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit
 * and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive
 * guesses and hints to eventually derive the secret number.
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 * Please note that both secret number and friend's guess may contain duplicate digits.
 * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），
 * 有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 *
 */

public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int[] secretNums = new int[10];
        int[] guessNums = new int[10];
        int correct = 0;
        for(int i = 0; i < secret.length(); i++) {
            int ns = secret.charAt(i) - '0';
            int ng = guess.charAt(i) - '0';
            if(ns == ng){
                correct++;
            }
            secretNums[ns]++;
            guessNums[ng]++;
        }
        int wrong = 0;
        for(int i = 0; i < 10; i++) {
            wrong += Math.min(secretNums[i], guessNums[i]);
        }
        return correct + "A" + (wrong - correct) + "B";
    }
}
