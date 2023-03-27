package com.algorithm.a.other;

public class SolutionV {

    public static void main(String[] args) {
          int[] nums = new int[]{2,0,2,1,1,0};
          sortColors(nums);
    }

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * <p>
     * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 必须在不使用库的sort函数的情况下解决这个问题。
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int n = nums.length;
        int num0 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[num0];
                nums[num0] = temp;
                num0++;
            }
        }
        int num1 = num0;
        for (int i = num1; i < n; i++) {
            if (nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[num1];
                nums[num1] = temp;
                num1++;
            }
        }
    }
}
