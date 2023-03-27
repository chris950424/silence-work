package com.algorithm.a.greedyalgorithm;

/**
 * @author Administrator
 */
public class Solution {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        final int i = maxArea(height);
        System.out.println(i);
    }

    public static int maxArea(int[] height) {
       int n =   height.length;
       int ans  = 0;
       int head = 0;
       int tail = n-1;
       while (head<tail){
           if (height[head]>=height[tail]){
               ans =  Math.max(ans,height[tail] * (tail - head));
               tail--;
           } else {
               ans =  Math.max(ans,height[head] * (tail - head));
               head++;
           }
       }
       return ans;
    }

}
