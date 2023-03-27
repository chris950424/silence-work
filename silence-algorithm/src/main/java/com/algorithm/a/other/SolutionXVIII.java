package com.algorithm.a.other;


import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionXVIII {

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        final int i = largestRectangleArea(heights);
        System.out.println(i);
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> monoStack = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            left[i] = (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }

        monoStack.clear();

        for (int i = n - 1; i >= 0; --i) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek());
            monoStack.push(i);
        }

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }

        return ans;
    }
}
