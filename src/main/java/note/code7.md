# LeetCode_7: 整数反转

给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。

# 示例

**示例 1：**

>输入：x = 123  
输出：321  

**示例 2：**

>输入：x = -123  
输出：-321

**示例 3：**

>输入：x = 120  
输出：21

**示例 4：**

>输入：x = 0  
输出：0


# 提示

- -231 <= x <= 231 - 1

# 解答
不断拿最后一位拼到前面去，`res * 10 + tmp` 处理
加到最后一位不要直接加，先判断会不会int越界，会直接返回0就行
```java
public class code7 {
    public int reverse(int x) {
        int flag = x >= 0 ? 1 : -1;
        int abx = Math.abs(x);
        int res = 0;
        while (abx > 0){
            int tmp = abx % 10;
            // 先判断
            if ((res > 214748364 && tmp > 7) || res > 214748365) return 0;
            res = res * 10 + tmp;
            abx = abx / 10;
        }
        if (res < 0) res = 0;
        res = flag * res;
        return res;
    }
}
```