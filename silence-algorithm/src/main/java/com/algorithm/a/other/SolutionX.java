package com.algorithm.a.other;

import java.util.Arrays;

public class SolutionX {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        int n1 = 0;
        int i = 0;
        while (i < n ) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[n1];
                nums[n1] = temp;
                n1++;
            }
            i++;
        }
        System.out.println(Arrays.toString(nums));
    }
}
