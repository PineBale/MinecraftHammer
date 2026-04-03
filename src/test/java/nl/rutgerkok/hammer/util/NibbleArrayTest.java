package nl.rutgerkok.hammer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NibbleArrayTest {

    @Test
    void testByteArrayLength() {
        // Check if byte array has correct size
        // (need 3 bites to fit 5 nibbles)
        NibbleArray nibbleArray = new NibbleArray(5);
        Assertions.assertEquals(3, nibbleArray.getHandle().length);
    }

    @Test
    void testGetFilledByte() {
        NibbleArray nibbleArray = new NibbleArray(new byte[] { (byte) 0xFF });
        Assertions.assertEquals(0xF, nibbleArray.get(0));
        Assertions.assertEquals(0xF, nibbleArray.get(1));
    }

    @Test
    void testGetOneByte() {
        // Premade byte, check returned nibbles using the methods
        NibbleArray nibbleArray = new NibbleArray(new byte[] { 0b0001__0000 });
        Assertions.assertEquals(0b0001, nibbleArray.get(1));
        Assertions.assertEquals(0b0000, nibbleArray.get(0));
    }

    @Test
    void testOverBounds() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            // One-and-a-half byte, so 3 nibbles, so index 3 is too high
            NibbleArray nibbleArray = new NibbleArray(3);
            nibbleArray.set(3, (byte) 1);
        });
    }

    @Test
    void testPositionOne() {
        // Sets a byte to 16 by setting the second position to 1
        NibbleArray nibbleArray = new NibbleArray(new byte[1]);
        nibbleArray.set(1, (byte) 1);

        byte result = nibbleArray.getHandle()[0];
        Assertions.assertEquals((byte) 0b0001__0000, result);
    }

    @Test
    void testPositionZero() {
        // Sets a byte to 1 by setting the first position to 1
        NibbleArray nibbleArray = new NibbleArray(new byte[1]);
        nibbleArray.set(0, (byte) 1);

        byte result = nibbleArray.getHandle()[0];
        Assertions.assertEquals((byte) 0b0000__0001, result);
    }

    @Test
    void testSetAndGetTwoBytes() {
        // Everything coded with the methods
        NibbleArray nibbleArray = new NibbleArray(4);
        nibbleArray.set(0, (byte) 0b0001);
        nibbleArray.set(1, (byte) 0b0010);
        nibbleArray.set(2, (byte) 0b0100);
        nibbleArray.set(3, (byte) 0b1000);

        Assertions.assertEquals(0b0001, nibbleArray.get(0));
        Assertions.assertEquals(0b0010, nibbleArray.get(1));
        Assertions.assertEquals(0b0100, nibbleArray.get(2));
        Assertions.assertEquals(0b1000, nibbleArray.get(3));
    }

    @Test
    void testSetTwoBytes() {
        // Setup using the methods, check for premade result
        NibbleArray nibbleArray = new NibbleArray(4);
        nibbleArray.set(0, (byte) 0b0001);
        nibbleArray.set(1, (byte) 0b0010);
        nibbleArray.set(2, (byte) 0b0100);
        nibbleArray.set(3, (byte) 0b1000);

        byte[] expected = new byte[] { 0b0010__0001, (byte) 0b1000__0100 };
        Assertions.assertArrayEquals(expected, nibbleArray.getHandle());
    }

    @Test
    void testTruncating() {
        // Test if second nibble is unaffected by setting the first nibble to a
        // value that is too high
        NibbleArray nibbleArray = new NibbleArray(new byte[1]);
        nibbleArray.set(1, (byte) 0b1111__1111);

        byte result = nibbleArray.getHandle()[0];
        Assertions.assertEquals((byte) 0b1111__0000, result);
    }

    @Test
    void testUnderBounds() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            // -1 is invalid position, obviously
            NibbleArray nibbleArray = new NibbleArray(new byte[1]);
            nibbleArray.set(-1, (byte) 1);
        });
    }

}
