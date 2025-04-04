# LeetCode_50：幂运算

实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。

# 示例

**示例 1：**

>输入：x = 2.00000, n = 10  
输出：1024.00000

**示例 2：**

>输入：x = 2.10000, n = 3  
输出：9.26100

**示例 3：**

>输入：x = 2.00000, n = -2  
输出：0.25000  
解释：2-2 = 1/22 = 1/4 = 0.25

# 提示

- -100.0 < x < 100.0
- -231 <= n <= 231-1
- n 是一个整数
- 要么 x 不为零，要么 n > 0 。
- -104 <= xn <= 104

# 解答
- 快速幂，利用位运算，1 2 4 8...
- 先将n转为正整数，最后再根据符号判断是否要取倒数
```java
public class code50 {
    public double myPow(double x, int n) {
        if (n == 0) return 1D;
        double zhengx = x;
        // int全局最小无法转为最大，需要-2147483648 -> 2147483647,最后再乘一次x
        int zhengn = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double res = 1;
        while (zhengn > 0) {
            if ((zhengn & 1) == 1)
                res *= zhengx;
            zhengx *= zhengx;
            zhengn >>= 1;
        }
        if (n == Integer.MIN_VALUE)
            res *= x;
        return n > 0 ? res : 1 / res;
    }
}
```