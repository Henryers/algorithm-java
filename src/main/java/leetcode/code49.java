package leetcode;

import java.util.*;

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
