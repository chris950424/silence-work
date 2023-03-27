package com.algorithm.a.other;


import java.util.HashMap;
import java.util.Map;

public class SolutionXXXII {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3};
        System.out.println(subarraySum(nums, 4));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
