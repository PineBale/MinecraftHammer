package nl.rutgerkok.hammer.anvil;

import nl.rutgerkok.hammer.Chunk;
import nl.rutgerkok.hammer.ChunkAccess;
import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import nl.rutgerkok.hammer.material.MaterialData;
import nl.rutgerkok.hammer.util.MaterialNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Tests getting a block at negative y.
 */
class Chunk117RandomAccessTest {

    private static AnvilWorld world;
    private int[] xyz = new int[] { 179, -49, -227 };

    @BeforeAll
    static void loadWorld() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_17_extended_height/level.dat");
        world = new AnvilWorld(new GlobalMaterialMap(), levelDat);
    }

    @Test
    void testChunkHeight() throws IOException, MaterialNotFoundException {
        try (ChunkAccess<?> chunkAccess = world.getChunkAccess()) {
            Chunk chunk = chunkAccess.getChunk(Math.floorDiv(xyz[0], AnvilChunk.CHUNK_X_SIZE), Math
                    .floorDiv(xyz[2], AnvilChunk.CHUNK_Z_SIZE));

            Assertions.assertEquals(-64, chunk.getDepth());
            Assertions.assertEquals(80, chunk.getHeight());
        }

    }

    @Test
    void testRetrieveChunk() throws IOException, MaterialNotFoundException {
        try (ChunkAccess<?> chunkAccess = world.getChunkAccess()) {
            Chunk chunk = chunkAccess.getChunk(Math.floorDiv(xyz[0], AnvilChunk.CHUNK_X_SIZE), Math
                    .floorDiv(xyz[2], AnvilChunk.CHUNK_Z_SIZE));

            // There must be bedrock at this location
            MaterialData materialData = chunk
                    .getMaterial(Math.floorMod(xyz[0], AnvilChunk.CHUNK_X_SIZE), xyz[1], Math
                            .floorMod(xyz[2], AnvilChunk.CHUNK_Z_SIZE));
            Assertions.assertEquals("minecraft:tuff", materialData.getName());
        }

    }
}
