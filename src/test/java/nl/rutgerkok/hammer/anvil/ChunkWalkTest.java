package nl.rutgerkok.hammer.anvil;

import nl.rutgerkok.hammer.CountingChunkVisitor;
import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class ChunkWalkTest {

    private static AnvilWorld world;

    @BeforeAll
    static void loadWorld() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_13/level.dat");
        world = new AnvilWorld(new GlobalMaterialMap(), levelDat);
    }

    @Test
    void testStatistics() throws IOException {
        CountingChunkVisitor chunkVisitor = new CountingChunkVisitor();
        world.walkChunks(chunkVisitor);
        Assertions.assertEquals(5014, chunkVisitor.chunksSeen.get());
        Assertions.assertEquals(427, chunkVisitor.entitiesSeen.get());
        Assertions.assertEquals(113, chunkVisitor.tileEntitiesSeen.get());
    }
}
