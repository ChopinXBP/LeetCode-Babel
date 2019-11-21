/**
 *
 * https://leetcode-cn.com/problems/valid-phone-numbers/
 * Given a text file file.txt that contains list of phone numbers (one per line), write a one liner bash script to print all valid phone numbers.
 * You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)
 * You may also assume each line in the text file must not contain leading or trailing white spaces.
 * 给定一个包含电话号码列表（一行一个电话号码）的文本文件 file.txt，写一个 bash 脚本输出所有有效的电话号码。
 * 你可以假设一个有效的电话号码必须满足以下两种格式： (xxx) xxx-xxxx 或 xxx-xxx-xxxx。（x 表示一个数字）
 * 你也可以假设每行前后没有多余的空格字符。
 *
 */

public class ValidPhoneNumbers {
}

/*

#!/bin/bash
#数据形式
#0(001) 345-0000
#(001) 345-0000
#123-456-789

#grep+正则表达式
grep -E "^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$|^[0-9]{3}-[0-9]{3}-[0-9]{4}$" file.txt

#awk+正则表达式
awk "/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/" file.txt

 */