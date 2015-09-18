package puzzle.ch1;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class StringCompress {
    public static String compress(String source) {
        StringBuilder compressed = new StringBuilder();
        int length = source.length();
        int repeat = 1;

        for (int i = 1; i < length; i++) {
            char prev = source.charAt(i - 1);

            if (source.charAt(i) == prev) repeat++;
            else {
                compressed.append(prev);
                compressed.append(repeat);

                if (compressed.length() > length)
                    return source;

                repeat = 1;
            }
        }

        compressed.append(source.charAt(length - 1));
        compressed.append(repeat);

        if (compressed.length() > length)
            return source;

        return compressed.toString();
    }

    @Test
    public void testStringCompress() {
        assertThat(compress("aabcccccaaa"), is("a2b1c5a3"));
        assertThat(compress("aaaabbc"), is("a4b2c1"));
        assertThat(compress("aaaaabbbbcd"), is("a5b4c1d1"));
        assertThat(compress("abc"), is("abc"));

        assertThat(compress("abcd"), is("abcd"));
        assertThat(compress("dont"), is("dont"));
    }
}
