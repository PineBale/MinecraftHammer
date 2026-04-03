package nl.rutgerkok.hammer.material;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaterialSetTest {

    @Test
    void testAdd() {
        GlobalMaterialMap dictionary = new GlobalMaterialMap();
        MaterialData material = dictionary.addMaterial(MaterialName.ofBaseName("test:foo"));

        MaterialSet set = new MaterialSet();
        Assertions.assertFalse(set.contains(material));

        // Now add it
        set.add(material);
        Assertions.assertTrue(set.contains(material));
    }

    @Test
    void testAddNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            MaterialSet set = new MaterialSet();
            set.add(null);
        });
    }

    @Test
    void testRemove() {
        GlobalMaterialMap dictionary = new GlobalMaterialMap();
        MaterialData material = dictionary.addMaterial(MaterialName.ofBaseName("test:foo"));

        MaterialSet set = new MaterialSet();
        set.add(material);

        // Remove it
        Assertions.assertTrue(set.remove(material));
        Assertions.assertFalse(set.contains(material));
    }

    @Test
    void testRemoveNonExistant() {
        MaterialSet set = new MaterialSet();

        // Remove it
        Assertions.assertFalse(set.remove(new Object()));
    }
}
