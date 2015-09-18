package puzzle.ch1;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringRotation {
    public static Boolean isRotation(String source, String test) {
        if (source.length() == test.length()) {
            String combined = source + test;
            return combined.contains(test);
        }

        return false;
    }

    @Test
    public void testStringRotation() {
        assertTrue(isRotation("waterbottle", "erbottlewat"));
        assertTrue(isRotation("testing", "ingtest"));

        assertFalse(isRotation("something", "else"));
        assertFalse(isRotation("another", "one"));
    }
}
