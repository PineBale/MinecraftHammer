package nl.rutgerkok.hammer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

class BooleanListTest {

    @Test
    void testAdd() {
        List<Boolean> booleanList = new BooleanList();
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);
        booleanList.addAll(Arrays.asList(false, false, false));

        Assertions.assertEquals(6, booleanList.size());
    }

    @Test
    void testDelete() {
        AbstractList<Boolean> booleanList = new BooleanList();
        booleanList.add(false);
        booleanList.add(true);
        booleanList.add(false);

        booleanList.remove(1);

        Assertions.assertEquals(Arrays.asList(false, false), booleanList);
    }

    @Test
    void testEqualsAndHashcode() {
        List<Boolean> booleanList = new BooleanList();
        booleanList.add(true);
        booleanList.add(false);
        booleanList.add(true);

        List<Boolean> arrayList = Arrays.asList(true, false, true);
        Assertions.assertEquals(arrayList, booleanList);

        // Call equals directly, as assertEquals calls equals on arrayList
        // instead of booleanList
        Assertions.assertTrue(booleanList.equals(arrayList));

        // Check the hash code too
        Assertions.assertEquals(booleanList.hashCode(), arrayList.hashCode(), "hashCode");
    }

    @Test
    void testInsert() {
        AbstractList<Boolean> booleanList = new BooleanList();
        booleanList.add(true);
        booleanList.add(true);
        booleanList.add(false);

        booleanList.add(1, false);

        Assertions.assertEquals(Arrays.asList(true, false, true, false), booleanList);
    }
}
