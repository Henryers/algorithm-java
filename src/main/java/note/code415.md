# LeetCode_415：字符串相加

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。

# 示例

**示例 1：**

>输入：num1 = "11", num2 = "123"  
输出："134"

**示例 2：**

>输入：num1 = "456", num2 = "77"  
输出："533"

**示例 3：**

>输入：num1 = "0", num2 = "0"  
输出："0"

# 提示

- 1 <= num1.length, num2.length <= 104
- num1 和num2 都只包含数字 0-9
- num1 和num2 都不包含任何前导零

# 解答
常规倒序双指针遍历，注意里面while写多个`||`，里面写三元组判断，这样不用提前退出再单独判断长度不一样的情况！

```java
public class code415 {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            res.append((x + y + add) % 10);
            add = (x + y + add) / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }
}
```