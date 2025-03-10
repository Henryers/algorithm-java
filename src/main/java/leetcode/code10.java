package leetcode;

public class code10 {
    public boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        boolean[][] dp = initDPMap(s, e);
        // 从右往左，整体从下往上
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
                } else {
                    int si = i;    // si++, 但dp中的i要 += 2, 所以要分开记录
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;  // 模拟*匹配的个数
                    }
                    dp[i][j] = dp[si][j + 2];    // while退出循环的最后一次情况会漏算，要补上
                }
            }
        }
        return dp[0][0];
    }

    // s = 5  e = 6 举例：
    // 初始化需要最后一行和最后两列
    // 最后两列只需要判断两个位置
    // x x x x x  F  F
    // x x x x x  F  F
    // x x x x x  F  F
    // x x x x x  F  F
    // x x x x x T/F F
    // T F T F T  F  T
    // a * a * a  *
    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        // dp[s][e]含义： s[i, ...] (往后所有)，能不能被 e[j, ...] (往后所有)配出来
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            // e[j] != '*' && 其实可以去掉，因为题目保证了不可能连续出现两个*
            if (e[j + 1] == '*') {
                dp[slen][j] = true;  // 空串都能匹配成功
            } else {
                break;  // s[slen, ...]空串，但是e[]其中至少存在一个字符且不能被*消掉，无法匹配
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }
}