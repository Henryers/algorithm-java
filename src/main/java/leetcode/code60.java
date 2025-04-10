package leetcode;

import java.util.*;

public class code60 {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }
        --k;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);       // 表示从1位置开始，所有数字都可用
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];   // 表示剩下的数选到正确的数append进去
                if (order == 0) {
                    ans.append(j);   // 减到为0代表找到 那个数  不可能！=0！否则排列不存在
                    valid[j] = 0;    // 表示用过了，之后不可用
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}
