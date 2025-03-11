package leetcode;

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
