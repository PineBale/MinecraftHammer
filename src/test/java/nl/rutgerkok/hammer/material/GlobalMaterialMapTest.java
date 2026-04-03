package nl.rutgerkok.hammer.material;

import nl.rutgerkok.hammer.util.MaterialNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;

class GlobalMaterialMapTest {

    @Test
    void testAliases() throws MaterialNotFoundException, ParseException {
        GlobalMaterialMap dictionary = new GlobalMaterialMap();
        MaterialData withAliases = dictionary.addMaterial(Arrays.asList(MaterialName.ofBaseName("minecraft:foo"),
                MaterialName.ofBaseName("minecraft:bar"), MaterialName.ofBaseName("minecraft:baz")));

        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.ofBaseName("minecraft:foo")));
        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.ofBaseName("minecraft:bar")));
        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.ofBaseName("minecraft:baz")));

        // For names without a Minecraft prefix, they must be case-insensitive
        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.parse("Foo")));
        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.parse("BAR")));
        Assertions.assertEquals(withAliases, dictionary.getMaterialByName(MaterialName.parse("bAz")));
    }

    @Test
    void testCombining() {
        // Add a material, then add second material that is equal according to
        // its alias. Test equality and test lookup of second name

        GlobalMaterialMap dictionary = new GlobalMaterialMap();
        MaterialData first = dictionary.addMaterial(MaterialName.ofBaseName("test:test"));
        MaterialData second = dictionary.addMaterial(
                Arrays.asList(MaterialName.ofBaseName("test:otherName"), MaterialName.ofBaseName("test:test")));

        Assertions.assertEquals(first, second);
        Assertions.assertEquals(first, dictionary.getMaterialByName(MaterialName.ofBaseName("test:otherName")));
    }

    @Test
    void testEquality() {
        // Registering two materials with different names should yield different
        // instances, but registering two materials with the same name should
        // yield equal instances.
        GlobalMaterialMap dictionary = new GlobalMaterialMap();

        MaterialData first = dictionary.addMaterial(MaterialName.ofBaseName("test:foo"));
        MaterialData second = dictionary.addMaterial(MaterialName.ofBaseName("test:bar"));
        MaterialData sameAsFirst = dictionary.addMaterial(MaterialName.ofBaseName("test:foo"));

        Assertions.assertNotEquals(first, second);
        Assertions.assertEquals(first, sameAsFirst);
    }
}
