package puzzle.ch2;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class DetectCircular {
    public static <T> Node<T> cycleStart(Node<T> list) {
        Node<T> iter, runner;
        iter = runner = list;

        while (runner != null && runner.next != null) {
            iter = iter.next;
            runner = runner.next.next;

            if (iter == runner) break;
        }

        if (runner == null || runner.next == null)
            return null;

        iter = list;

        while (iter != runner) {
            iter = iter.next;
            runner = runner.next;
        }

        return runner;
    }

    @Test
    public void testDedup() {
        Node<Integer> e = new Node<>(5, null);
        Node<Integer> d = new Node<>(4, e);
        Node<Integer> c = new Node<>(3, d);
        Node<Integer> b = new Node<>(2, c);
        Node<Integer> a = new Node<>(1, b);

        e.next = c;

        Node<Integer> start = cycleStart(a);

        assertThat(c, is(start));
    }
}
