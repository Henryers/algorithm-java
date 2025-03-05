package leetcode;

public class code8 {
    public static int myAtoi(String s) {
        int flag = 1;
        boolean hasNum = false;
        boolean hasop = false;
        int res = 0;
        s = s.trim();  // 去除前导空格
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int n = (int) c - 48;
            // 空格
            if (c == ' ' && (hasNum || hasop)) {
                return flag * res;
            }
            // 符号
            else if (c == '+' || c == '-') {
                if (hasNum || hasop) return flag * res;
                else {
                    flag = c == '+' ? 1 : -1;
                    hasop = true;
                }
            }
            // 数字
            else if (0 <= n && n <= 9) {
                if (res > 2147483647 / 10 || (res == 2147483647 / 10 && n > 7)) {
                    return flag == 1 ? 2147483647 : -2147483648;
                }
                res = res * 10 + n;
                hasNum = true;
            } else {
                break;
            }
        }
        return flag * res;
    }

    public static void main(String[] args) {
        String s = "  -0897d 4";
        System.out.println(myAtoi(s));
    }
}
