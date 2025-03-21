package leetcode;

import java.util.*;

public class code29 {
    public static int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1)    return Integer.MIN_VALUE;
            if (divisor == -1)   return Integer.MAX_VALUE;
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE)
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        // 考虑被除数为 0 的情况
        if (dividend == 0)    return 0;

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况（负数会多一个数）
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index)*2);        // 成倍增加
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {  // 类似 ... 8 4 2 1
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }
        return rev ? -ans : ans;
    }

    public static void main(String[] args) {
        int res = divide(10, 3);
        System.out.println(res);
    }
}
