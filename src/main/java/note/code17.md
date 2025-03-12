# LeetCode_17：电话号码的字母组合

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。

# 示例

**示例 1：**

>输入：digits = "23"  
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]  

**示例 2：**

>输入：digits = ""  
输出：[]  

**示例 3：**

>输入：digits = "2"  
输出：["a","b","c"]


# 提示

- 0 <= digits.length <= 4
- digits[i] 是范围 ['2', '9'] 的一个数字。

# 解答
- 第一想法就是要存进map
- 循环+递归，加一个字符就进递归，回溯时要清空当前加的字符，用于当前位置在下一个循环选其他字母
```java
public class code17 {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.isEmpty()) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer tmp) {
        if (index == digits.length()) {
            combinations.add(tmp.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                tmp.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, tmp);
                tmp.deleteCharAt(index);
            }
        }
    }
}
```