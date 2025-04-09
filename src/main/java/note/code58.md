# LeetCode58：最后一个单词的长度

输入字符串由英文字母和空字符串组成

# 解答

还要题解？诗人啊

```java
public class code58 {
    public int lengthOfLastWord(String s) {
        int res = 0;
        s = s.trim();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') res++;
            else break;
        }
        return res;
    }
}
```