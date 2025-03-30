# LeetCode43：字符串相乘

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

# 示例

**示例 1:**

>输入: num1 = "2", num2 = "3"  
输出: "6"  
示例 2:

>输入: num1 = "123", num2 = "456"  
输出: "56088"

# 提示

- 1 <= num1.length, num2.length <= 200
- num1 和 num2 只能由数字组成。
- num1 和 num2 都不包含任何前导零，除了数字0本身。

# 解答
加法乘法两种，乘法属于没做过根本想不起来的...

```java
public class code43 {
    // 乘法写法，m、n位置倒换来做相加都可以，原理一样
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        // 先全部一个个乘好相加，放到对应桶里（可能会出现每个桶的数都是两位数）
        for (int i = n - 1; i >= 0; i--) {
            int x = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int y = num1.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        // 再对桶中的进位信息进行处理，放到另一个桶
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        // 看最高位的索引是0还是1？（index = 1 说明数字相乘后仍然过小，最高位是0，位数少一位）
        int index = ansArr[0] == 0 ? 1 : 0;
        // 拼接字符串后返回
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    // 加法写法
//    public String multiply(String num1, String num2) {
//        if (num1.equals("0") || num2.equals("0")) {
//            return "0";
//        }
//        String ans = "0";
//        int m = num1.length(), n = num2.length();
//        for (int i = n - 1; i >= 0; i--) {
//            StringBuffer curr = new StringBuffer();
//            int add = 0;
//            for (int j = n - 1; j > i; j--) {
//                curr.append(0);
//            }
//            int y = num2.charAt(i) - '0';
//            for (int j = m - 1; j >= 0; j--) {
//                int x = num1.charAt(j) - '0';
//                int product = x * y + add;
//                curr.append(product % 10);
//                add = product / 10;
//            }
//            if (add != 0) {
//                curr.append(add % 10);
//            }
//            ans = addStrings(ans, curr.reverse().toString());
//        }
//        return ans;
//    }
//
    // LeetCode415 字符串相加
//    public String addStrings(String num1, String num2) {
//        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
//        StringBuffer ans = new StringBuffer();
//        while (i >= 0 || j >= 0 || add != 0) {
//            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
//            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
//            int result = x + y + add;
//            ans.append(result % 10);
//            add = result / 10;
//            i--;
//            j--;
//        }
//        ans.reverse();
//        return ans.toString();
//    }
}
```