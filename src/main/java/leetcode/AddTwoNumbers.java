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

public class AddTwoNumbers {
    public int singleNumberXOR(int[] nums) {
        int running = nums[0];

        for (int i = 1; i < nums.length; i++)
            running ^= nums[i];

        return running;
    }

    public int singleNumber(int[] nums) {
        Set set = new TreeSet<Integer>();
        Set dupe = new TreeSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) set.add(nums[i]);
            else {
                set.remove(nums[i]);
                dupe.add(nums[i]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!dupe.contains(nums[i])) return nums[i];
        }

        return nums[0];
    }

    @Test
    public void testThing() {}
}
