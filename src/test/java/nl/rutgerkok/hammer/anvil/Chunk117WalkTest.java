package nl.rutgerkok.hammer.anvil;

import nl.rutgerkok.hammer.CountingChunkVisitor;
import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class Chunk117WalkTest {

    private static AnvilWorld world;

    @BeforeAll
    static void loadWorld() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_17_extended_height/level.dat");
        world = new AnvilWorld(new GlobalMaterialMap(), levelDat);
    }

    @Test
    void testStatistics() throws IOException {
        CountingChunkVisitor chunkVisitor = new CountingChunkVisitor();
        world.walkChunks(chunkVisitor);
        Assertions.assertEquals(1706, chunkVisitor.chunksSeen.get());
        Assertions.assertEquals(304, chunkVisitor.entitiesSeen.get());
        Assertions.assertEquals(43, chunkVisitor.tileEntitiesSeen.get());
    }
}
