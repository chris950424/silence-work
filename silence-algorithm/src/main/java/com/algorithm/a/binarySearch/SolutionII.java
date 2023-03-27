package com.algorithm.a.binarySearch;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class SolutionII {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        final int[] ints = searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
    }


    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        Arrays.fill(ans, -1);
        final int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                l = mid;
                while (l > -1 && nums[l] == target) {
                    ans[0] = l;
                    l--;
                }
                r = mid;
                while (r < n && nums[r] == target) {
                    ans[1] = r;
                    r++;
                }
                break;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

}
