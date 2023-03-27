package com.algorithm.a.other;


import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionXVIV {

    public static void main(String[] args) {

        char[][] matrix = new char[][]
                {{'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}};

        final int i = maximalRectangle(matrix);
        System.out.println(i);

    }

    public static int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if (n <= 0) {
            return n;
        }
        int m = matrix[0].length;
        int[][] left = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = matrix[i][j];
                if (c == '0') {
                    continue;
                }
                left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    if (width != 0) {
                        area = Math.max(area, (i - k + 1) * width);
                    }
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
}
