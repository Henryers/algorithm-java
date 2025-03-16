# LeetCode_22：括号生成

数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

# 示例

**示例 1：**

>输入：n = 3  
输出：["((()))","(()())","(())()","()(())","()()()"]

**示例 2：**

>输入：n = 1  
输出：["()"]


# 提示

- 1 <= n <= 8

# 解答

用递归，当前位置递归后有回溯delete，但l已经++，所以后面 --l 和 ++r 同时出现
```java
public class code22 {
    public List<String> generateParenthesis(int n) {
        StringBuilder s = new StringBuilder();
        List<String> res = new ArrayList<>();
        process(n, 2 * n, s, res, 0, 0);
        return res;
    }

    public String process(int max, int n, StringBuilder s, List<String> res, int l, int r) {
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
```