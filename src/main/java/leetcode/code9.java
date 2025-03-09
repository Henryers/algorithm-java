package leetcode;

import java.util.ArrayList;
import java.util.List;

public class code9 {
    public boolean isPalindrome(int x) {
        // 简单粗暴字符串
//        String s = x + "";
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < chars.length/2; i++) {
//            if (chars[i] != chars[chars.length - i -1]) return false;
//        }
//        return true;

        // 反转一半数字，省空间
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reverse_num = 0;
        while (x > reverse_num){
            reverse_num = reverse_num * 10 + x % 10;
            x /= 10;
        }
        return reverse_num == x || reverse_num / 10 == x;
    }
}
