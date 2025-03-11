# LeetCode_14：最长公共前缀

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

# 示例

**示例 1：**

>输入：strs = ["flower","flow","flight"]  
输出："fl"  
示例 2：

>输入：strs = ["dog","racecar","car"]  
输出：""  
解释：输入不存在公共前缀。


# 提示

- 1 <= strs.length <= 200
- 0 <= strs[i].length <= 200
- strs[i] 如果非空，则仅由小写英文字母组成

# 解答
纵向扫描秒了
```java
public class code14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {  // i 列索引
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {  // j 行索引
                // 如果列索引越界 / 字符不匹配，返回当前匹配好的子串
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);  // [0,i)
                }
            }
        }
        return strs[0];
    }
}

```