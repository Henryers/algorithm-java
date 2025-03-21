# LeetCode_29：两数相除

给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。

整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。

返回被除数 dividend 除以除数 divisor 得到的 商 。

注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，则返回 231 − 1 ；如果商 严格小于 -231 ，则返回 -231 。

# 示例

**示例 1:**

>输入: dividend = 10, divisor = 3  
输出: 3  
解释: 10/3 = 3.33333.. ，向零截断后得到 3 。

**示例 2:**

>输入: dividend = 7, divisor = -3  
输出: -2  
解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。


# 提示

- -231 <= dividend, divisor <= 231 - 1
- divisor != 0

# 解答
- 和T50的快速幂类似，这里说是二分其实更准确来说是快速乘
- 快速幂是累乘，快速乘是累加1+2+4+8...

```java
public class code29 {
    public int divide(int dividend, int divisor) {
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
        // 注意溢出（本来就是candidates.get(index)*2 >= dividend，但是不能用乘除法）
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
}
```