package leetcode;

import java.util.HashMap;

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

    public static void main(String[] args) {
        String str = "abba";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
