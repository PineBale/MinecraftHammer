package nl.rutgerkok.hammer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class NumberMapTest {

    @Test
    void testAddInvalidZeroBinding() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NumberMap numberMap = new NumberMap();
            numberMap.put((char) 10, (char) 0);
        });
    }

    @Test
    void testAddZeroBinding() {
        NumberMap numberMap = new NumberMap();
        numberMap.put((char) 0, (char) 0);
        Assertions.assertEquals(0, numberMap.getTranslatedId((char) 0));
    }

    @Test
    void testBasics() {
        NumberMap numberMap = new NumberMap();
        numberMap.put((char) 200, (char) 30);
        Assertions.assertEquals(30, numberMap.getTranslatedId((char) 200));
    }

    @Test
    void testNonExistent() throws MaterialNotFoundException {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            NumberMap numberMap = new NumberMap();
            numberMap.getTranslatedId((char) 200);
        });
    }
}
