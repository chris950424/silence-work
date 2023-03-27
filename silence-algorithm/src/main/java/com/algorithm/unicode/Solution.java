package com.algorithm.unicode;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
      String str = "I am 君山";
        final char[] chars = str.toCharArray();
        final byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(chars);
        System.out.println(Arrays.toString(bytes));
    }

}
