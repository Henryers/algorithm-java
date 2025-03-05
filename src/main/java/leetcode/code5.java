package leetcode;

public class code5 {
    public static String longestPalindrome(String s) {
        char[] charArr = s.toCharArray();
        char[] str = new char[s.length() * 2 + 1]; // 带#的字符串
        int index = 0;
        // apple -> #a#p#p#l#e#  奇数位全部插入#标记
        for (int i = 0; i != str.length; i++) {
            str[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        // 回文半径数组   pArr[i] 以i为中心的，带#的回文半径  如 #1#2#1#  2的回文半径是 2#1#，长度为4
        int[] pArr = new int[str.length];
        int maxC = -1; // 最长回文子串的中心位置
        int C = -1;    // 最右回文右边界对应回文串的中心位置（注意不是最长回文子串的中心！）
        int R = -1;    // 当前最右回文右边界的下一个位置，右边界是R-1
        int max = Integer.MIN_VALUE;    // 扩出来的最大值（半径最大长度）
        for (int i = 0; i != str.length; i++) {   // 每一个位置都求回文半径

            // 关键代码：i位置不用验的，至少的回文区域！ 该部分是O(n^2) -> O(n)的关键步骤！
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 1. i在R外 为1
            // 2. i在R内： 2.1 左侧回文区域在R内 pArr[2 * C - i]
            //             2.2 左侧回文区域超过大回文边界  R - i

            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 中心i +/- 回文半径(包括i自己) = 左/右边界， 看往外扩时，最左和最右是否相等
                // 相等就 ++ 否则就定了，直接跳出循环
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else
                    break;   // 两种不用扩的情况也都写在扩的代码中，即使扩一次也会失败，直接break了，能让代码更短！
            }
            // 更新 maxC  max    #1#2#1#    pArr[4] = 4
            if (pArr[i] >= max) {
                maxC = i;
                max = pArr[i];
            }
            // 如果半径最右边R更往右了，那就更新 C、R
            if (i + pArr[i] > R) {
                R = i + pArr[i]; // 所以R显然是最大回文右边界的下一个字符
                C = i;
            }
        }
        max = max - 1;  // 带#的回文串半径-1 = 不带#的回文串长度
        // 处理字串，已知：max,maxC
        StringBuilder result = new StringBuilder();
        for (int i = maxC - max; i < maxC + max; i++) {
            if (str[i] != '#')
                result.append(str[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "abdsadbcbdbd";
        System.out.println(longestPalindrome(s));
    }
}