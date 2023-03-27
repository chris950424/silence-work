package com.algorithm.a.Tree;

public class SolutionVII {


    public static void main(String[] args) {
        int i = numTrees(2);
        System.out.println(i);
    }

    public static int numTrees(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }
}
