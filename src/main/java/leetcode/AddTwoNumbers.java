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
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        public String toString() {
            StringBuilder builder = new StringBuilder();
            ListNode current = this;

            while (current != null) {
                builder.append(" " + current.val + " ->");
                current = current.next;
            }

            return builder.toString();
        }

        public boolean equals(Object o) {
            return this.toString().equals(((ListNode)o).toString());
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode tail = null;
        boolean carry = false;

        while (carry || l1 != null || l2 != null) {
            if (carry && l1 == null && l2 == null) {
                tail.next = new ListNode(1);
                break;
            }

            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int valCarry = carry ? 1 : 0;
            int sum = val1 + val2 + valCarry;

            if (sum >= 10) {
                sum -= 10;
                carry = true;
            } else carry = false;

            ListNode node = new ListNode(sum);

            if (result == null) result = tail = node;
            else                tail = tail.next = node;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return result;
    }

    @Test
    public void testThing() {
        ListNode l1 = new ListNode(2);
        ListNode l1B = new ListNode(4);
        l1.next = l1B;
        ListNode l1C = new ListNode(3);
        l1B.next = l1C;

        ListNode l2 = new ListNode(5);
        ListNode l2B = new ListNode(6);
        l2.next = l2B;
        ListNode l2C = new ListNode(4);
        l2B.next = l2C;

        ListNode l3 = new ListNode(7);
        ListNode l3B = new ListNode(0);
        l3.next = l3B;
        ListNode l3C = new ListNode(8);
        l3B.next = l3C;

        assertThat(addTwoNumbers(l1, l2), is(l3));
    }
}
