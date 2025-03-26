package leetcode;

public class code38 {

    public String countAndSay(int n) {
        if (n == 1) return "1";
        String preEncode = countAndSay(n - 1);
        // 回溯阶段：preEncode 的行程长度编码为 countAndSay(n) 的 result
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i < preEncode.length(); i++) {
            if (preEncode.charAt(i) == preEncode.charAt(i - 1)) {
                count++;
            } else {
                result.append(count);
                result.append(preEncode.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count);
        result.append(preEncode.charAt(preEncode.length() - 1));
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "q";
        // 测试能不能直接加数字字符
        s += 2;
        System.out.println(s);
    }
}
