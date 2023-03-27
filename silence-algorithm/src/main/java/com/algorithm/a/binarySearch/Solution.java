package com.algorithm.a.binarySearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
//        int[] nums = new int[]{1};

        final int search = search(nums, 0);
        System.out.println(search);
    }


    public static int search(int[] nums, int target) {
        final int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1 && nums[0] == target) {
            return 0;
        }
        int le = 0;
        int ri = n - 1;
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target<nums[mid]) {
                    ri = mid - 1;
                } else {
                    le = mid + 1;
                }
            } else {
                if (nums[mid] < target && target<nums[ri]) {
                    le = mid + 1;
                } else {
                    ri = mid - 1;
                }
            }
        }
        return -1;
    }
}
