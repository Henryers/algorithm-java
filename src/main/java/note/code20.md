# LeetCode_20：有效的括号

给定一个只包括 `'('，')'，'{'，'}'，'['，']'` 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。  
左括号必须以正确的顺序闭合。  
每个右括号都有一个对应的相同类型的左括号。

# 示例

**示例 1：**

>输入：s = "()"  
输出：true

**示例 2：**

>输入：s = "()[]{}"  
输出：true

**示例 3：**

>输入：s = "(]"  
输出：false

**示例 4：**

>输入：s = "([])"  
输出：true


# 提示

- 1 <= s.length <= 104
- s 仅由括号 '()[]{}' 组成

# 解答
- 左括号入栈、右括号出栈，出栈时判断是否合法即可
- map 用来记录左括号和右括号的对应关系，在判断时去map拿，看看能不能匹配上
- 由于题目要求需要按正确的顺序闭合，因此第一个弹出的必须匹配上，否则顺序乱了！因此弹栈时只需判断一次，不用写while！

```java
public class code20 {
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        Stack<Character> s1 = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(int i = 0; i < str.length; i++){
            if (str[i] == '(' || str[i] == '[' || str[i] == '{'){
                s1.push(str[i]);
            }else {
                if(s1.isEmpty() || map.get(s1.pop()) != str[i]) return false;
            }
        }
        return s1.isEmpty();
    }
}
```