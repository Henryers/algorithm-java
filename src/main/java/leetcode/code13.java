package leetcode;

import java.util.HashMap;

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
