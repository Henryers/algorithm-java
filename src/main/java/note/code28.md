# LeetCode_28：找出字符串中第一个匹配项的下标

给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。

# 示例

**示例 1：**

>输入：haystack = "sadbutsad", needle = "sad"  
输出：0  
解释："sad" 在下标 0 和 6 处匹配。  
第一个匹配项的下标是 0 ，所以返回 0 。

**示例 2：**

>输入：haystack = "leetcode", needle = "leeto"  
输出：-1  
解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。


# 提示

- 1 <= haystack.length, needle.length <= 104
- haystack 和 needle 仅由小写英文字符组成

# 解答
`KMP`算法，我出过视频教程的hhh，自己看自己~
```java
public class code28 {
    public static int strStr(String s, String m) {
        if (s == null || m == null || m.isEmpty() || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (i2 == 0) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] str) {
        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
```