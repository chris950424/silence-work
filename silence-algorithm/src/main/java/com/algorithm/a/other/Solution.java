package com.algorithm.a.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,4};
        final int[] ints = twoSum(nums, 4);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>(8);
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target-nums[i])!= null){
                return new int[]{map.get(target-nums[i]),i,};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }

}
