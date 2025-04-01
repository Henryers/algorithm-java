# LeetCode_44：通配符匹配

给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符序列（包括空字符序列）。
判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。

# 示例

**示例 1：**

>输入：s = "aa", p = "a"  
输出：false  
解释："a" 无法匹配 "aa" 整个字符串。

**示例 2：**

>输入：s = "aa", p = "*"  
输出：true  
解释：`'*'` 可以匹配任意字符串。

**示例 3：**

>输入：s = "cb", p = "?a"  
输出：false  
解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。

# 提示

- 0 <= s.length, p.length <= 2000
- s 仅由小写英文字母组成
- p 仅由小写英文字母、'?' 或 '*' 组成

# 解答
动态规划比较好理解，另一个太难了...
不要想那么复杂，动态规划就考虑`*`和非`*`的情况依赖，还有初始化，其他别管!
```java
public class code44 {
    public boolean isMatch(String s, String p) {
        // 简单动态规划，比较慢但现阶段比较好理解
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
```