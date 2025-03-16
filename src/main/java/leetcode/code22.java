package leetcode;

import java.util.*;

public class code22 {
    public static List<String> generateParenthesis(int n) {
        StringBuilder s = new StringBuilder();
        List<String> res = new ArrayList<>();
        process(n, 2 * n, s, res, 0, 0);
        return res;
    }

    public static String process(int max, int n, StringBuilder s, List<String> res, int l, int r) {
        if (n == 0 || l < r) {
            if (r == l) {
                res.add(s.toString());
            }
            return "";  // 有可能是 l < r 的情况，当前右括号已经过多，匹配失败，返回空字符串
        }
        // 我靠明明这步更好懂为什么更耗时！！！
        // if (l < r) return "";
        // if (n == 0 && r == l) res.add(s.toString());

        if (l <= max) {
            s.append("(");
            process(max, n - 1, s, res, ++l, r);
            s = s.deleteCharAt(s.length() - 1);
        }
        if (l > r) {
            s.append(")");
            // 因为上一步其实已经delete了当前数了，但是l已经++，所以这次r++的同时要把l--回去！
            process(max, n - 1, s, res, --l, ++r);
            s = s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }
}
