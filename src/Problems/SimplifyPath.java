package Problems;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.
 * For more information, see: Absolute path vs relative path in Linux/Unix
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 */

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        LinkedList<String> list = new LinkedList<>();

        for (int i = 0; i < strs.length; i++) {
            if (!list.isEmpty() && strs[i].equals("..")){
                list.pollLast();
            }
            else if (!(strs[i].equals("") || strs[i].equals(".") || strs[i].equals(".."))){
                list.add(strs[i]);
            }
        }
        if (list.isEmpty()){
            return "/";
        }
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append("/" + s);
        }
        return result.toString();
    }
}
