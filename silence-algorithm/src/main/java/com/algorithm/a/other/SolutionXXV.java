package com.algorithm.a.other;

import java.util.Deque;
import java.util.LinkedList;

public class SolutionXXV {

    public static void main(String[] args) {
        int abcabcbb = lengthOfLongestSubstring("abcabcbb");
        System.out.println(abcabcbb);
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] re = new int[128];
        int pre = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (re[c] == 0) {
                length = Math.max(length, i - pre + 1);
            } else {
                for (int j = pre; j < i; j++) {
                    char c1 = s.charAt(j);
                    --re[c1];
                    if (c == c1) {
                        pre = j + 1;
                        break;
                    }
                }
            }
            ++re[c];
        }
        return length;
    }
}
