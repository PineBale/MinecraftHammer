package nl.rutgerkok.hammer.material;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Collections;

class MaterialNameTest {

    @Test
    void basics() {
        MaterialName test = MaterialName.ofBaseName("test:foo");
        Assertions.assertEquals("test:foo", test.getBaseName());
        Assertions.assertEquals(Collections.emptyMap(), test.getProperties());
        Assertions.assertEquals("test:foo", test.toString());
    }

    @Test
    void fullNamesCannotAccidentallyBeUsedAsBaseNames() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MaterialName.ofBaseName("minecraft:grass[snowy=false]");
        });
    }

    @Test
    void parsingMultipleProperties() throws ParseException {
        MaterialName dispenserDown = MaterialName.parse("minecraft:dispenser[facing=down,triggered=false]");
        MaterialName dispenser2Down = MaterialName.parse("minecraft:dispenser[triggered=false,facing=down]");
        MaterialName dispenserUp = MaterialName.parse("minecraft:dispenser[facing=up,triggered=false]");

        Assertions.assertEquals(dispenserDown, dispenser2Down);
        Assertions.assertEquals(ImmutableMap.of("facing", "down", "triggered", "false"), dispenserDown.getProperties());
        Assertions.assertNotEquals(dispenserDown, dispenserUp);
        Assertions.assertTrue(dispenserDown.toString().contains(","));
    }

    @Test
    void parsingWithMissingAssignmentOperator() {
        Assertions.assertThrows(ParseException.class, () -> {
            MaterialName.parse("minecraft:grass[snowy]");
        });
    }

    @Test
    void parsingWithMissingClosingBracket() {
        Assertions.assertThrows(ParseException.class, () -> {
            MaterialName.parse("minecraft:grass[snowy=false");
        });
    }

    @Test
    void parsingWithoutPrefix() throws ParseException {
        MaterialName dirt = MaterialName.parse("DIRT");

        Assertions.assertEquals("minecraft:dirt", dirt.getBaseName());
        Assertions.assertEquals(ImmutableMap.of(), dirt.getProperties());
        Assertions.assertEquals("minecraft:dirt", dirt.toString());
    }

    @Test
    void parsingWithProperties() throws ParseException {
        MaterialName grass = MaterialName.parse("minecraft:grass[snowy=false]");

        Assertions.assertEquals("minecraft:grass", grass.getBaseName());
        Assertions.assertEquals(ImmutableMap.of("snowy", "false"), grass.getProperties());
        Assertions.assertEquals("minecraft:grass[snowy=false]", grass.toString());
    }

    @Test()
    void prefixIsObligatory() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            MaterialName.ofBaseName("foo");
        });
    }
}
