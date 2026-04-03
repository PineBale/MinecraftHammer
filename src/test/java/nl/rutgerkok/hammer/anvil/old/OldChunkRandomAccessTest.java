package nl.rutgerkok.hammer.anvil.old;

import nl.rutgerkok.hammer.Chunk;
import nl.rutgerkok.hammer.ChunkAccess;
import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.anvil.AnvilWorld;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import nl.rutgerkok.hammer.material.MaterialData;
import nl.rutgerkok.hammer.util.MaterialNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Tests random (as opposed to sequential) access to chunks.
 */
class OldChunkRandomAccessTest {

    private static AnvilWorld world;

    @BeforeAll
    static void loadWorld() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_7_10/level.dat");
        world = new AnvilWorld(new GlobalMaterialMap(), levelDat);
    }

    @Test
    void testChunkHeight() throws IOException, MaterialNotFoundException {
        try (ChunkAccess<?> chunkAccess = world.getChunkAccess()) {
            Chunk chunk = chunkAccess.getChunk(0, 9);

            Assertions.assertEquals(0, chunk.getDepth());
            Assertions.assertEquals(80, chunk.getHeight());
        }

    }

    @Test
    void testRetrieveChunk() throws IOException, MaterialNotFoundException {
        try (ChunkAccess<?> chunkAccess = world.getChunkAccess()) {
            Chunk chunk = chunkAccess.getChunk(0, 9);

            // There must be bedrock at layer 0
            MaterialData materialData = chunk.getMaterial(0, 0, 0);
            Assertions.assertEquals("minecraft:bedrock", materialData.getName());
        }

    }
}
