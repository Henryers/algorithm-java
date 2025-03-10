# LeetCode_13：罗马字符转整数
罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

| 符号 | 	值    |
|----|-------|
| I  | 	1    |
| V  | 	5    |
| X  | 	10   |
| L  | 	50   |
| C  | 	100  |
| D  | 	500  |
| M  | 	1000 |

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。  
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。  
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。  
给定一个罗马数字，将其转换成整数。

# 示例

**示例 1:**

>输入: s = "III"  
输出: 3

**示例 2:**

>输入: s = "IV"  
输出: 4

**示例 3:**

>输入: s = "MCMXCIV"  
输出: 1994  
解释: M = 1000, CM = 900, XC = 90, IV = 4.


# 提示

- 1 <= s.length <= 15
- s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
- 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
- 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
- IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
- 关于罗马数字的详尽书写规则，可以参考 罗马数字 - 百度百科。

# 解答
存map对应关系，正常遍历，当遇到4、9时单独判断，`-value` 而不是正常的 `+value`
```java
public class code13 {
    public int romanToInt(String s) {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int value = map.get(c);
            if (i != s.length() - 1 && value < map.get(s.charAt(i+1))) res -= value;
            else res += value;
        }
        return res;
    }
}
```