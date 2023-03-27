package com.algorithm.a.greedyalgorithm;

public class SolutionIII {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 5, 3, 8, 6, 9};
//        int[] prices = new int[]{7, 6, 5, 4, 3, 2};
        final int i = maxProfit(prices);
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
