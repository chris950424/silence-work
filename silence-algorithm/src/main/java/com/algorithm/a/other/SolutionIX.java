package com.algorithm.a.other;

import java.util.Arrays;

public class SolutionIX {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int n1 = 0;
        int i = n - 1;
        while (i >= n1) {
            if (nums[i] == 0) {
                i--;
            } else {
                int temp = nums[i];
                nums[i] = nums[n1];
                nums[n1] = temp;
//                for (int j = n1; j > 0; j--) {
//                    if (nums[j] < nums[j - 1]) {
//                        temp = nums[j];
//                        nums[j] = nums[j - 1];
//                        nums[j - 1] = temp;
//                    }
//                }
                n1++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
