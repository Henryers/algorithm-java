# LeetCode_6：Z字形变换

将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
```
P   A   H   N
A P L S I I G
Y   I   R
```
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

> string convert(string s, int numRows);

# 示例

**示例 1：**

>输入：s = "PAYPALISHIRING", numRows = 3  
输出："PAHNAPLSIIGYIR"

**示例 2：**

>输入：s = "PAYPALISHIRING", numRows = 4  
输出："PINALSIGYAHRPI"  
>解释：
>```
>P     I    N
>A   L S  I G
>Y A   H R
>P     I
>```

**示例 3：**

>输入：s = "A", numRows = 1  
输出："A"

# 提示

- 1 <= s.length <= 1000
- s 由英文字母（小写和大写）、',' 和 '.' 组成
- 1 <= numRows <= 1000

# 解答
找规律，n个数为一组  
每组又分为自顶向下、自底向上两个部分（PANet幻视...）
```java
public class code6 {
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) return s;
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        // r * 2 - 2 表示一个周期的数，比如4行，一个周期就是6个数
        // √ 0            √ 6
        // √ 1       √ 5  √ 7
        // √ 2  √ 4       ...
        // √ 3
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) { // 当前i 0 1 2 小于 3
                ++x;             // 接下来要填的数(1 2 3)是在下一行
            } else {             // 当前i 3 4 5 不小于 3
                --x;             // 接下来要填的数(4 5 6)是在上一行
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }
}
```