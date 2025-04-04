# LeetCode_49：字母异位词分组

给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

# 示例

**示例 1:**

>输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]  
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

**示例 2:**

>输入: strs = [""]  
输出: [[""]]

**示例 3:**

>输入: strs = ["a"]  
输出: [["a"]]

# 提示

- 1 <= strs.length <= 104
- 0 <= strs[i].length <= 100
- strs[i] 仅包含小写字母

# 解答
直接用个map，一样异味词的用相同的key（即排序好的字符串），每个key维护一个String列表就行！
```java
public class code49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charstr = str.toCharArray();
            Arrays.sort(charstr);
            String key = new String(charstr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // 此时必有key，找到对应的key，加进它的list即可
            List<String> list = map.get(key);
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
```