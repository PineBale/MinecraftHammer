package nl.rutgerkok.hammer.anvil.old;

import nl.rutgerkok.hammer.material.BlockDataMaterialMap;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import nl.rutgerkok.hammer.material.MaterialData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

class OldMaterialMapTest {

    private static BlockDataMaterialMap map;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        map = new BlockDataMaterialMap(new GlobalMaterialMap(), Paths.get("src/main/resources/blocks_pc_1_12.json").toUri().toURL());
    }

    @Test
    void testMissingProperties() {
        MaterialData material = map.getMaterialData("minecraft:dropper", (byte) 1);
        Assertions.assertEquals("minecraft:dropper[facing=up,triggered=false]", material.toString());
    }

    @Test
    void testNewNameFromOld() {
        MaterialData material = map.getGlobal().getMaterialByName("minecraft:stained_hardened_clay[color=red]");
        Assertions.assertEquals("minecraft:red_terracotta", material.toString());
        Assertions.assertEquals("minecraft:stained_hardened_clay", map.getCanonicalMinecraftName(material).getBaseName());
        Assertions.assertEquals(159 * 16 + 14, map.getMinecraftId(material));

    }

    @Test
    void testNewNameFromOld2() {
        MaterialData material = map.getGlobal().getMaterialByName("minecraft:dirt[variant=podzol]");
        Assertions.assertEquals("minecraft:podzol", material.toString());
        Assertions.assertEquals("minecraft:dirt[variant=podzol]", map.getCanonicalMinecraftName(material).toString());
        Assertions.assertEquals(3 * 16 + 2, map.getMinecraftId(material));
    }

    @Test
    void testOldNameFromNew() {
        MaterialData material = map.getGlobal().getMaterialByName("minecraft:red_terracotta");
        Assertions.assertEquals("minecraft:red_terracotta", material.toString());
        Assertions.assertEquals("minecraft:stained_hardened_clay", map.getCanonicalMinecraftName(material).getBaseName());
    }
}
