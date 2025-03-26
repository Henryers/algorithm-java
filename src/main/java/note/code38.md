# LeetCode_38：外观数列

「外观数列」是一个数位字符串序列，由递归公式定义：

countAndSay(1) = "1"  
countAndSay(n) 是 countAndSay(n-1) 的行程长度编码。


行程长度编码（RLE）是一种字符串压缩方法，其工作原理是通过将连续相同字符（重复两次或更多次）替换为字符重复次数（运行长度）和字符的串联。例如，要压缩字符串 "3322251" ，我们将 "33" 用 "23" 替换，将 "222" 用 "32" 替换，将 "5" 用 "15" 替换并将 "1" 用 "11" 替换。因此压缩后字符串变为 "23321511"。

给定一个整数 n ，返回 外观数列 的第 n 个元素。

# 示例

**示例 1：**

>输入：n = 4  
输出："1211"  
解释：
countAndSay(1) = "1"
countAndSay(2) = "1" 的行程长度编码 = "11"  
countAndSay(3) = "11" 的行程长度编码 = "21"  
countAndSay(4) = "21" 的行程长度编码 = "1211"

**示例 2：**

>输入：n = 1  
输出："1"  
解释：  
这是基本情况。

# 提示

- 1 <= n <= 30

# 解答

- 递归，根据题意，在回溯时处理上一个输出`String`的行程长度编码
- 迭代反而更慢？
- 官方题解枚举30个结果有点逆天hhh

```java
public class code38 {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String preEncode = countAndSay(n - 1);
        // 回溯阶段：preEncode 的行程长度编码为 countAndSay(n) 的 result
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < preEncode.length(); i++) {
            if (preEncode.charAt(i) == preEncode.charAt(i - 1)) {
                count++;
            } else {
                result.append(count);
                result.append(preEncode.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count);
        result.append(preEncode.charAt(preEncode.length() - 1));
        return result.toString();

        // 试了下迭代但是更慢...
        StringBuilder preEncode = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            StringBuilder tmp = new StringBuilder();
            int count = 1;
            for (int j = 1; j < preEncode.length(); j++) {
                if (preEncode.charAt(j) == preEncode.charAt(j - 1)) {
                    count++;
                } else {
                    tmp.append(count);
                    tmp.append(preEncode.charAt(j - 1));
                    count = 1;
                }
            }
            tmp.append(count);
            tmp.append(preEncode.charAt(preEncode.length() - 1));
            preEncode = tmp;
        }
        return preEncode.toString();
    }
}
```