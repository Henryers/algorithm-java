package leetcode;

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

//        // 右侧先决策，防止一开始左侧算一大堆无用功
//        int sRight = s.length(), pRight = p.length();  // 注意此时指的是 长度（=索引+1）
//        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
//            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
//                --sRight;
//                --pRight;
//            } else {
//                return false;
//            }
//        }
//        // p变为空串时，s是否也是匹配完变成空串 ？true : false
//        if (pRight == 0) {
//            return sRight == 0;
//        }
//        // 从左侧开始算起
//        int sIndex = 0, pIndex = 0;       // 双指针
//        int sRecord = -1, pRecord = -1;   // 回溯到的记录点
//
//        while (sIndex < sRight && pIndex < pRight) {
//            if (p.charAt(pIndex) == '*') {   // 贪心，只匹配0个字符（sIndex不++）
//                ++pIndex;
//                sRecord = sIndex;
//                pRecord = pIndex;
//            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {  // 把?也封装一起
//                ++sIndex;
//                ++pIndex;
//            } else if (sRecord != -1 && sRecord + 1 < sRight) {
//                ++sRecord;            // 尝试多匹配一个字符（最外层有一层循环）
//                sIndex = sRecord;     // 回溯到Record点，继续比对
//                pIndex = pRecord;
//            } else {
//                return false;
//            }
//        }
//
//        return allStars(p, pIndex, pRight);
    }

    // 看看此时模式串p来到的*位置是不是最开始pRight的*位置？
    // 如果是那太好了，拿捏任意母字符串s（这种情况下s的剩余未匹配部分才可能不是空串，否则一定是空串！）
    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;      // left位置的*和right位置的*之间要么长度为1（left == right）
            }                      // 要么都为***... 否则出现 *xx*无法与空串s匹配！
        }
        return true;
    }

    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }
}
