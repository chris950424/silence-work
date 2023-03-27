package com.algorithm.a.greedyalgorithm;

/**
 * @author Administrator
 */
public class SolutionII {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 5, 3, 8, 6, 9};
        maxProfit(prices, 2);
    }

    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}
