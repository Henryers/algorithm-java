# LeetCode_3：无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。

# 示例

**示例 1:**

>输入: s = "abcabcbb"  
输出: 3  
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。  

**示例 2:**

>输入: s = "bbbbb"  
输出: 1  
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。  

**示例 3:**

>输入: s = "pwwkew"  
输出: 3  
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。  
请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


# 提示

- 0 <= s.length <= 5 * 104  
- s 由英文字母、数字、符号和空格组成

# 解答
**双指针**：r一直++，l根据记录在map中的位置进行移动
```java
public class code3 {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        int l = 0;
        int r = 0;
        int res = 0;
        while (r < str.length) {
            if (map.containsKey(str[r])) {
                // 拿到该重复字符的前一个位置索引+1，有可能<当前的l，所以再套个max
                l = Math.max(map.get(str[r]) + 1, l);
            }
            map.put(str[r], r);
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
}
```