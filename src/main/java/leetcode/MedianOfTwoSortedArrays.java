package puzzle.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

import java.util.Queue;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;

        if (length % 2 == 0) {
            double left = smallestK(nums1, 0, nums2, 0, length / 2);
            double right = smallestK(nums1, 0, nums2, 0, (length / 2) + 1);

            return (left + right) / 2.0;
        } else
            return smallestK(nums1, 0, nums2, 0, (length / 2) + 1);
    }

    public double smallestK(int a[], int a_start, int b[], int b_start, int k) {
        int a_remains = a.length - a_start;
        int b_remains = b.length - b_start;

        if (a_remains > b_remains) {
            return smallestK(b, b_start, a, a_start, k);
        }

        if (a_remains <= 0) {
            return b[b_start + k - 1];
        }

        if (k == 1) {
            return Math.min(a[a_start], b[b_start]);
        }

        int a_partition = Math.min(k / 2, a_remains);
        int b_partition = k - a_partition;

        if (a[a_start + a_partition - 1] <= b[b_start + b_partition - 1]) {
            return smallestK(a, a_start + a_partition,
                             b, b_start,
                             k - a_partition);
        } else {
            return smallestK(a, a_start,
                             b, b_start + b_partition,
                             k - b_partition);
        }
    }

    @Test
    public void testThing() {}
}
