package nl.rutgerkok.hammer.anvil.old;

import nl.rutgerkok.hammer.CountingChunkVisitor;
import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.anvil.AnvilWorld;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class OldChunkWalkTest {

    private static AnvilWorld world;

    @BeforeAll
    static void loadWorld() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_7_10/level.dat");
        world = new AnvilWorld(new GlobalMaterialMap(), levelDat);
    }

    @Test
    void testStatistics() throws IOException {
        CountingChunkVisitor chunkVisitor = new CountingChunkVisitor();
        world.walkChunks(chunkVisitor);
        Assertions.assertEquals(575, chunkVisitor.chunksSeen.get());
        Assertions.assertEquals(611, chunkVisitor.entitiesSeen.get());
        Assertions.assertEquals(17, chunkVisitor.tileEntitiesSeen.get());
    }
}
