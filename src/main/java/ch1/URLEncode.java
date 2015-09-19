package ch1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class URLEncode {
    public static void encode(char[] str) {
        int riter, end;
        riter = end = str.length - 1;

        for (int liter = end; liter >= 0; liter--) {
            if (str[liter] != ' ' || riter != end) {
                if (str[liter] != ' ') {
                    str[riter--] = str[liter];
                } else {
                    str[riter--] = '0';
                    str[riter--] = '2';
                    str[riter--] = '%';
                }
            }
        }
    }

    @Test
    public void testEliminate() {
        char[] test1 = "Mr John Smith    ".toCharArray();
        encode(test1);

        assertThat(test1, is("Mr%20John%20Smith".toCharArray()));

        char[] test2 = "one two three    ".toCharArray();
        encode(test2);

        assertThat(test2, is("one%20two%20three".toCharArray()));
    }
}
