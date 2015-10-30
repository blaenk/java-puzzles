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

public class ExpressionAddOperators {
    static class Crumb {
        String builder;
        int position;
        long total;
        long last;

        Crumb(String trail, int position, long total, long last) {
            this.builder = trail;
            this.position = position;
            this.total = total;
            this.last = last;
        }

        Crumb(Crumb other) {
            this.builder = other.builder;
            this.total = other.total;
            this.position = other.position;
            this.last = other.last;
        }

        public String toString() {
            return String.format("\"%s\" = %d @ %d [last: %d]", this.builder, this.total, this.position, this.last);
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        Deque<Crumb> frontier = new ArrayDeque<>();

        if (num == null || num.length() == 0) return results;

        char[] chars = num.toCharArray();

        for (int i = 0; i < num.length(); i++) {
            if (i != 0 && chars[0] == '0') break;
            String sub = num.substring(0, i + 1);
            long initial = Long.parseLong(sub);
            frontier.addLast(new Crumb(sub, i + 1, initial, initial));
        }

        do {
            Crumb popped = frontier.pollLast();

            for (int i = popped.position; i < num.length(); i++) {
              if (i != popped.position && chars[popped.position] == '0') break;
              String sub = num.substring(popped.position, i + 1);
              Long current = Long.parseLong(sub);

              Crumb multed = new Crumb(popped);
              multed.builder += "*" + sub;
              multed.position = i + 1;
              multed.total -= multed.last;
              multed.total += multed.last * current;
              multed.last *= current;

              if (multed.position >= num.length()) {
                  if (multed.total == target)
                    results.add(multed.builder);
              }
              else frontier.addLast(multed);

              Crumb added = new Crumb(popped);
              added.builder += "+" + sub;
              added.position = i + 1;
              added.total += current;
              added.last = current;

              if (added.position >= num.length()) {
                  if (added.total == target)
                    results.add(added.builder);
              }
              else frontier.addLast(added);

              Crumb subtracted = new Crumb(popped);
              subtracted.builder += "-" + sub;
              subtracted.position = i + 1;
              subtracted.total -= current;
              subtracted.last = -current;

              if (subtracted.position >= num.length()) {
                  if (subtracted.total == target)
                    results.add(subtracted.builder);
              }
              else frontier.addLast(subtracted);
            }
        } while (!frontier.isEmpty());

        return results;
    }

    @Test
    public void testThing() {
        List<String> results = new ArrayList<>();
        results.add("1+2+3");
        results.add("1*2*3");

        assertThat(addOperators("123", 6), is(results));

        List<String> results3 = new ArrayList<>();
        results3.add("2+3*2");
        results3.add("2*3+2");

        assertThat(addOperators("232", 8), is(results3));

        List<String> results4 = new ArrayList<>();
        results4.add("10-5");
        results4.add("1*0+5");

        assertThat(addOperators("105", 5), is(results4));

        List<String> results2 = new ArrayList<>();
        results2.add("0*0");
        results2.add("0+0");
        results2.add("0-0");

        assertThat(addOperators("00", 0), is(results2));

        List<String> results5 = new ArrayList<>();
        assertThat(addOperators("3456237490", 9191), is(results5));

        List<String> results6 = addOperators("123456789", 45);
        assertThat(results6.size(), is(121));
    }
}
