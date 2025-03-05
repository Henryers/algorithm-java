package leetcode;

public class code6 {
    public static String convert(String s, int numRows) {
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

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int r = 3;
        System.out.println(convert(s, r));
    }
}
