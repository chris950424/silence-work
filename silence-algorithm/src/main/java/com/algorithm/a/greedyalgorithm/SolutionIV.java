package com.algorithm.a.greedyalgorithm;

public class SolutionIV {

//    输入：nums = [2,3,1,1,4]
//    输出：true
//    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

    public static void main(String[] args) {
        int[] n = new int[]{2, 2, 1, 0, 4};
        final boolean b = canJump(n);
        System.out.println(b);
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
