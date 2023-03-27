package com.algorithm.a.other;

/**
 * @author Administrator
 */
public class SolutionII {

    public static void main(String[] args) {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{2,3};
        final double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        double midNum;
        int n = n1 + n2;
        int mid = (n) / 2;
        if (n % 2 == 0) {
            midNum = (findMedianSorted(nums1, nums2, mid - 1) + findMedianSorted(nums1, nums2, mid)) / 2;
        } else {
            midNum = findMedianSorted(nums1, nums2, mid);
        }
        return midNum;
    }

    public static double findMedianSorted(int[] nums1, int[] nums2, int mid) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int n = n1+n2;
        int midNum = 0;
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; i++) {
            if (i1 < n1 && i2 < n2) {
                if (nums1[i1] < nums2[i2]) {
                    if ((i1 + i2) == mid) {
                        midNum = nums1[i1];
                    }
                    i1++;
                } else {
                    if ((i1 + i2) == mid) {
                        midNum = nums2[i2];
                    }
                    i2++;
                }
            }
            if (i1 < n1 && i2 >= n2) {
                if ((i1 + i2) == mid) {
                    midNum = nums1[i1];
                }
                i1++;
            }
            if (i2 < n2 && i1 >= n1) {
                if ((i1 + i2) == mid) {
                    midNum = nums2[i2];
                }
                i2++;
            }

        }
        return midNum;
    }

}
