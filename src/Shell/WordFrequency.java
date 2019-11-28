package Shell;

/**
 *
 * Write a bash script to calculate the frequency of each word in a text file words.txt.
 * For simplicity sake, you may assume:
 * words.txt contains only lowercase characters and space ' ' characters.
 * Each word must consist of lowercase characters only.
 * Words are separated by one or more whitespace characters.
 * 写一个 bash 脚本以统计一个文本文件 words.txt 中每个单词出现的频率。
 * 为了简单起见，你可以假设：
 * words.txt只包括小写字母和 ' ' 。
 * 每个单词只由小写字母组成。
 * 单词间由一个或多个空格字符分隔。
 *
 */

public class WordFrequency {
}

/*

#!/bin/bash
# xargs 分割字符串 -n 1表示每行输出一个 可以加-d指定分割符
# 要使用uniq统计词频需要被统计文本相同字符前后在一起，所以先排序 uniq -c 表示同时输出出现次数
# sort -nr 其中-n表示把数字当做真正的数字处理(当数字被当做字符串处理，会出现11比2小的情况)
cat words.txt | xargs -n 1 | sort | uniq -c | sort -nr | awk '{print $2" "$1}'

# 在awk中我们用一个字典count储存每个单词的词频，先遍历每一行(awk自身机制)的每一个字段(i<=NF)，
# 然后用该字段本身作为key,将其value++；最后用一个for循环输出count数组中的每个元素的key(词)及其value(词频)。
# 关于sort命令：-r是倒序排序，-n是将字符串当作numeric数值排序，-k则指定用于排序的字段位置，后跟2指将第二位的count[k](词频)作为排序的key
cat words.txt |
awk '{
    for(i=1;i<=NF;i++){
        count[$i]++
    }
} END {
    for(k in count){
        print k" "count[k]
    }
}' |
sort -rnk 2

 */
