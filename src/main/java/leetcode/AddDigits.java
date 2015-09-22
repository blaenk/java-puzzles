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

public class AddDigits {
    public int addDigits(int num) {
        if ((num / 10) < 1) return num;

        int sum = 0;

        do {
            sum = 0;

            do {
                sum += num % 10;
                num /= 10;
            } while (num >= 1);

            num = sum;
        } while ((sum / 10) >= 1);

        return sum;
    }

    public int addDigitsRecursive(int num) {
        if ((num / 10) < 1) return num;
        else {
            int sum = 0;
            do {
                sum += num % 10;
                num /= 10;
            } while (num >= 1);

            return addDigitsRecursive(sum);
        }
    }

    @Test
    public void testThing() {
        assertThat(addDigits(38), is(2));
        assertThat(addDigitsRecursive(38), is(2));
    }
}
