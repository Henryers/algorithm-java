package leetcode;

import java.util.*;

public class code32 {
    public int longestValidParentheses(String s) {
        int res = 0;
        // Deque的LinkedList非同步，不如stack安全，但是快，开销少
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);  // 初始化栈，确保第一个如果是)的话，弹出不会报这个错："栈中无元素"
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // 压入的是此时左括号对应的下标索引
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // )无法在栈中找到匹配的(，压栈，记录最长有效括号相邻左边的元素，同时保证不会报错："栈中无元素"
                } else {
                    res = Math.max(res, i - stack.peek());
                    // 栈最顶的元素的索引与当前索引之差，就是有效的，匹配好的最长括号
                }
            }
        }
        return res;

        // 或者用动态规划更快
        // int maxans = 0;
        // int[] dp = new int[s.length()];
        // for (int i = 1; i < s.length(); i++) {
        //     if (s.charAt(i) == ')') {
        //         if (s.charAt(i - 1) == '(') {
        //             dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
        //         } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
        //             // 左括号 ( 之前的部分
        //             int pre = ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);
        //             // pre +  (  +  中间的部分 +  ) ,   即  pre + 中间 + 2
        //             dp[i] = pre + dp[i - 1] + 2;
        //         }
        //         maxans = Math.max(maxans, dp[i]);
        //     }
        // }
        // return maxans;
    }
}
