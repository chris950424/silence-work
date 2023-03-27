package com.algorithm.a.other;

import java.util.*;

public class SolutionXXI {



    /*    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]
        解释：
        滑动窗口的位置                最大值
    ---------------               -----
            [1  3  -1] -3  5  3  6  7       3
            1 [3  -1  -3] 5  3  6  7       3
            1  3 [-1  -3  5] 3  6  7       5
            1  3  -1 [-3  5  3] 6  7       5
            1  3  -1  -3 [5  3  6] 7       6
            1  3  -1  -3  5 [3  6  7]      7*/


    public static void main(String[] args) {
//        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = new int[]{1, -1};

        int[] ints = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (i - deque.getFirst() >= k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

}
