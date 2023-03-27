package com.algorithm.a.other;

import java.util.*;

/**
 * @author Administrator
 */
public class SolutionXVI {

    public static void main(String[] args) {
        boolean valid = isValid("){");
        System.out.println(valid);
    }

    private static final Map<Character, Character> MAP = new HashMap<Character, Character>() {{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};


    public static boolean isValid(String s) {
        int length = s.length();
        if (length <= 1) {
            return false;
        }
        boolean flag = true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (MAP.containsValue(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (MAP.get(c) != pop) {
                    flag = false;
                }
            }
        }
        if (!stack.isEmpty()) {
            flag = false;
        }
        return flag;
    }
}
