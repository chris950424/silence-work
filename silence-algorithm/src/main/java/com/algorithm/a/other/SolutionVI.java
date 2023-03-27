package com.algorithm.a.other;

public class SolutionVI {

    public static void main(String[] args) {
        String s = "ACAAAAAA";
        String t = "AA";
        String s1 = minWindow(s, t);
        System.out.println(s1);
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] have = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int firstPoint = 0;
        int secondPoint = 0;
        int count = 0;
        int start = 0;
        int minSize = Integer.MAX_VALUE;
        while (firstPoint < s.length()) {
            char c = s.charAt(firstPoint);
            if (need[c] != 0) {
                if (have[c] < need[c]) {
                    count++;
                }
            }
            have[c]++;
            firstPoint++;
            while (count == t.length() && secondPoint <= firstPoint) {
                if (firstPoint - secondPoint <= minSize) {
                    start = secondPoint;
                    minSize = firstPoint - start;
                }
                char c1 = s.charAt(secondPoint);
                if (need[c1] != 0) {
                    if ((have[c1] = have[c1] - 1) < need[c1]) {
                        count--;
                    }
                }
                secondPoint++;
            }
        }
        if (minSize > s.length()) {
            return "";
        }
        return s.substring(start, start + minSize);
    }
}
