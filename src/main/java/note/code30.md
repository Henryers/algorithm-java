# LeetCode_30：串联所有单词的子串

给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。

s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。

例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。

# 示例

**示例 1：**

>输入：s = "barfoothefoobarman", words = ["foo","bar"]  
输出：[0,9]  
解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
输出顺序无关紧要。返回 [9,0] 也是可以的。

**示例 2：**

>输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]  
输出：[]  
解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
所以我们返回一个空数组。

**示例 3：**

>输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]  
输出：[6,9,12]  
解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。


# 提示

- 1 <= s.length <= 104
- 1 <= words.length <= 5000
- 1 <= words[i].length <= 30
- words[i] 和 s 由小写英文字母组成

# 解答
问了ds猜知道为啥外层遍历n而不是m...
```
对于每一种划分方式，滑动窗口的大小为 6，每次移动 n = 3 个字符。
例如，从 s[0] 开始划分：
窗口 0-5：子串为 "barfoo"，分割成 ["bar", "foo"]，与 words 匹配，记录索引 0。
窗口 3-8：子串为 "foothe"，分割成 ["foo", "the"]，不匹配。
窗口 6-11：子串为 "thefoo"，分割成 ["the", "foo"]，不匹配。
窗口 9-14：子串为 "foobar"，分割成 ["foo", "bar"]，与 words 匹配，记录索引 9。
```
滑动n个单位，右滑添加单词，同时左边移出，减去单词
词频表刚开始有初始化，只有当其开头的串联子串都匹配到才能返回start值，否则只能等下一个开始位置

```java
public class code30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            // 第一遍初始化
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            // 滑动窗口，一次滑动n个，即一个字串的距离
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {  // 第一次不用，上面初始化好了第一次
                    // 右增
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    // 左减
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }
}
```