package com.algorithm.a.other;

import java.util.*;

/**
 *  SolutionXXVII
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/6/18
 */
public class SolutionXXVII {

    public static void main(String[] args) {
        String[] stirs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(stirs);
        System.out.println(lists);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String s : strs) {
            char[] chars =s.toCharArray();
            Arrays.sort(chars);
            String s1 = String.valueOf(chars);
            List<String> orDefault = map.getOrDefault(s1, new ArrayList<>());
            orDefault.add(s);
            map.put(s1, orDefault);
        }
        return new ArrayList<>(map.values());
    }

}
