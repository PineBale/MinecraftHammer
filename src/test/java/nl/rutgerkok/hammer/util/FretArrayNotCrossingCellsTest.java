package nl.rutgerkok.hammer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FretArrayNotCrossingCellsTest {

    @Test
    void negativePositionIsNotAllowed() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            long[] array = { 3689348814741905697L, 3689348814741910323L };
            Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 4, -1));
        });
    }

    @Test
    void testFiveBits() {
        // 5 bits sometimes need to be distributed over two longs
        long[] array = { 0, 0 }; // Enough room for 24 entries
        FretArray.notCrossingCellBoundaries().set(array, 5, 12, (char) 23);
        Assertions.assertEquals("{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}",
                FretArray.notCrossingCellBoundaries().toString(array, 5));
    }

    @Test
    void testGet() {
        long[] array = { 3689348814741905697L, 3689348814741910323L };
        Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 4, 0));
        Assertions.assertEquals(2, FretArray.notCrossingCellBoundaries().get(array, 4, 1));
        Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 4, 2));
        Assertions.assertEquals(2, FretArray.notCrossingCellBoundaries().get(array, 4, 3));
        Assertions.assertEquals(3, FretArray.notCrossingCellBoundaries().get(array, 4, 31));
    }

    @Test
    void testLongArrayLength() {
        Assertions.assertEquals(2, FretArray.notCrossingCellBoundaries().getLongArrayLength(128, 1));
        Assertions.assertEquals(2, FretArray.notCrossingCellBoundaries().getLongArrayLength(64, 2));
        Assertions.assertEquals(2, FretArray.notCrossingCellBoundaries().getLongArrayLength(16, 8));

        Assertions.assertEquals(5, FretArray.notCrossingCellBoundaries().getLongArrayLength(5, 64));

        Assertions.assertEquals(410, FretArray.notCrossingCellBoundaries().getLongArrayLength(4096, 6));

    }

    @Test
    void testSet() {
        // Change the first four positions so that there are only 4-bit 3's in
        // the array
        long[] array = { 3689348814741905697L, 3689348814741910323L };
        FretArray.notCrossingCellBoundaries().set(array, 4, 0, (char) 3);
        FretArray.notCrossingCellBoundaries().set(array, 4, 1, (char) 3);
        FretArray.notCrossingCellBoundaries().set(array, 4, 2, (char) 3);
        FretArray.notCrossingCellBoundaries().set(array, 4, 3, (char) 3);

        Assertions.assertEquals(3689348814741910323L, array[0]);
    }

    @Test
    void testSetTooBigValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // 16 does not fit in four bits
            long[] array = { 3689348814741905697L, 3689348814741910323L };
            FretArray.notCrossingCellBoundaries().set(array, 4, 0, (char) 16);
        });
    }

    @Test
    void testSeventeenBitsIsNotAllowed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // A char is only 16 bit, so 17 bits won't work
            long[] array = { 3689348814741905697L, 3689348814741910323L };
            Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 17, 1));
        });
    }

    @Test
    void testToString() {
        long[] array = {0, 0};
        FretArray.notCrossingCellBoundaries().set(array, 4, 0, (char) 3);
        FretArray.notCrossingCellBoundaries().set(array, 4, 1, (char) 5);
        FretArray.notCrossingCellBoundaries().set(array, 4, 2, (char) 2);
        FretArray.notCrossingCellBoundaries().set(array, 4, 3, (char) 15);
        Assertions.assertEquals(
                "{3, 5, 2, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}",
                FretArray.notCrossingCellBoundaries().toString(array, 4));
    }

    @Test
    void testZeroBitsIsNotAllowed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            long[] array = { 3689348814741905697L, 3689348814741910323L };
            Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 0, 1));
        });
    }


    @Test
    void tooBigPositionIsNotAllowed() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            long[] array = { 3689348814741905697L, 3689348814741910323L };
            Assertions.assertEquals(1, FretArray.notCrossingCellBoundaries().get(array, 4, 32));
        });
    }
}
