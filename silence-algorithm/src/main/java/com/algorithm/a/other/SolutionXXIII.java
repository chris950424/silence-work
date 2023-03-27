package com.algorithm.a.other;


import java.util.Arrays;

public class SolutionXXIII {


    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        final int[] ints = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));
    }

    /* 给定一个整数数组 temperatures，
       表示每天的温度，返回一个数组 answer，
       其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
       如果气温在这之后都不会升高，请在该位置用 0 来代替。
    */


    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[i] < temperatures[j]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }
}
