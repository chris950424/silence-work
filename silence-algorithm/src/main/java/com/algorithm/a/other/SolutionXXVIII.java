package com.algorithm.a.other;

import java.util.*;

/**
 * SolutionXXVII
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/6/18
 */
public class SolutionXXVIII {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
//        int[] nums = new int[]{0, 0};

        final int i = longestConsecutive(nums);
        System.out.println(i);
    }

    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        int lIndex = 0;
        for (int i = 1; i < n; i++) {
            if (list.get(lIndex) != nums[i]) {
                list.add(nums[i]);
                lIndex++;
            }
        }

        int longNum = 1;
        int pre = 0;
        int lastNum = nums[0];

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == lastNum + 1) {
                if (i - pre + 1 > longNum) {
                    longNum = i - pre + 1;
                }
            } else {
                pre = i;
            }
            lastNum = list.get(i);
        }
        return longNum;
    }
}
