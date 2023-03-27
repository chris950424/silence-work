package com.algorithm.a.greedyalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SolutionVI {

    public static void main(String[] args) {
        int[][] people = new int[][]{
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        };
//        int[][] people = new int[][]{
//                {6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}
//
//        };


        final int[][] ints = reconstructQueue(people);
        System.out.println(Arrays.deepToString(ints));
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                return person2[1] - person1[1];
            }
        });
        final int n = people.length;
        int[][] queue = new int[n][];
        for (int[] person : people) {
            int space = person[1] + 1;
            for (int i = 0; i < n; i++) {
                if (queue[i] == null) {
                    --space;
                    if (space == 0) {
                        queue[i] = person;
                        break;
                    }
                }
            }
        }
        return queue;
    }
}
