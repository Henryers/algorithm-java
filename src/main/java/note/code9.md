# LeetCode_9：判断回文数

给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是。

# 示例

**示例 1：**

>输入：x = 121  
输出：true

**示例 2：**

>输入：x = -121  
输出：false  
解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

**示例 3：**

>输入：x = 10  
输出：false  
解释：从右向左读, 为 01 。因此它不是一个回文数。

# 提示

- -231 <= x <= 231 - 1

# 解答

```java
public class code9 {
    public boolean isPalindrome(int x) {
        // 方法1：简单粗暴字符串
        String s = x + "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            if (chars[i] != chars[chars.length - i -1]) return false;
        }
        return true;
        
        // 方法2：反转一半数字，省空间
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reverse_num = 0;
        while (x > reverse_num){
            reverse_num = reverse_num * 10 + x % 10;
            x /= 10;
        }
        return reverse_num == x || reverse_num / 10 == x;
    }
}
```