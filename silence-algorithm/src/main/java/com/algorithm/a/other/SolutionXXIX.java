package com.algorithm.a.other;

import java.util.*;

/**
 * SolutionXXVII
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/6/18
 */
public class SolutionXXIX {

    public static void main(String[] args) {
        int[] nums = new int[]{0, -3, 7, 2, 5, 8, 4, 6, 0, 1};
//        int[] nums = new int[]{0, 0};

        final int i = longestConsecutive(nums);
        System.out.println(i);
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;

    }
}
