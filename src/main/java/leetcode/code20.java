package leetcode;

import java.util.HashMap;
import java.util.Stack;

public class code20 {
    public static boolean isValid(String s) {
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

    public static void main(String[] args) {
        String s = "({})";
        System.out.println(isValid(s));
    }
}
