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
        Node<Integer> a = new Node<>();
        Node<Integer> b = new Node<>();
        Node<Integer> c = new Node<>();
        Node<Integer> d = new Node<>();
        Node<Integer> e = new Node<>();

        a.value = 1;
        b.value = 2;
        c.value = 3;
        d.value = 4;
        e.value = 5;

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;

        Node<Integer> start = cycleStart(a);

        assertThat(c, is(start));
    }
}
