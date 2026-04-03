package nl.rutgerkok.hammer.anvil;

import nl.rutgerkok.hammer.util.MaterialNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ChunkDataVersionTest {

    @Test
    void testAfter() throws IOException, MaterialNotFoundException {
        Assertions.assertFalse(ChunkDataVersion.MINECRAFT_1_12_2.isAfter(ChunkDataVersion.MINECRAFT_1_13));
        Assertions.assertFalse(ChunkDataVersion.MINECRAFT_1_12_2.isAfter(ChunkDataVersion.MINECRAFT_1_12_2));
        Assertions.assertTrue(ChunkDataVersion.MINECRAFT_1_12_2.isAfter(ChunkDataVersion.MINECRAFT_1_12_1));
    }

    @Test
    void testBefore() throws IOException, MaterialNotFoundException {
        Assertions.assertTrue(ChunkDataVersion.MINECRAFT_1_12_2.isBefore(ChunkDataVersion.MINECRAFT_1_13));
        Assertions.assertFalse(ChunkDataVersion.MINECRAFT_1_12_2.isBefore(ChunkDataVersion.MINECRAFT_1_12_2));
        Assertions.assertFalse(ChunkDataVersion.MINECRAFT_1_12_2.isBefore(ChunkDataVersion.MINECRAFT_1_12_1));
    }
}
