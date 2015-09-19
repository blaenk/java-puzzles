package puzzle.ch2;

import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class DedupList {
    public static <T> void dedupList(List<T> list) {
        int it = 0;

        while (it < list.size()) {
            T current = list.get(it);
            int runner = it + 1;

            while (runner < list.size()) {
                if (current == list.get(runner))
                    list.remove(runner);
                else
                    runner++;
            }

            it++;
        }
    }

    @Test
    public void testDedup() {
        List<Integer> list1 = new LinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(4);
        list1.add(5);

        List<Integer> target = new LinkedList<>();
        target.add(1);
        target.add(2);
        target.add(3);
        target.add(4);
        target.add(5);

        dedupList(list1);

        assertThat(list1, is(target));
    }
}
