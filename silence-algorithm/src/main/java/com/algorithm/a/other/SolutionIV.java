package com.algorithm.a.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SolutionIV
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/4/29
 */
public class SolutionIV {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = -nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j -1]) {
                    continue;
                }
                int k = nums.length - 1;

                while (j < k && nums[j] + nums[k] > remain) {
                    k--;
                }
                if (j == k) {
                    break;
                }
                if (remain == nums[j] + nums[k]) {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[k]);
                    list.add(list1);
                }
            }
        }
        return list;
    }
}
