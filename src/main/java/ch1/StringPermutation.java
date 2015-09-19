package puzzle.ch1;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class StringPermutation {
    public static boolean isPermutation(String source, String test) {
        Map<Character, Integer> chars = new TreeMap<>();

        for (int i = 0; i < source.length(); i++)
            chars.merge(source.charAt(i), 1, Integer::sum);

        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);

            if (!chars.containsKey(c)) return false;

            int count = chars.get(c);

            if (--count < 0) return false;

            chars.put(c, count);
        }

        return true;
    }

    @Test
    public void testPermutation() {
        assertTrue(isPermutation("permutation", "peiorunttma"));
        assertTrue(isPermutation("another", "hnrotae"));
        assertTrue(isPermutation("forgoodmeasure", "emooodsrafeurg"));

        assertFalse(isPermutation("notthis", "definitelynot"));
        assertFalse(isPermutation("pleaseno", "okfine"));
    }
}
