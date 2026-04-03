package nl.rutgerkok.hammer.anvil.old;

import nl.rutgerkok.hammer.GlobalTempResourceManager;
import nl.rutgerkok.hammer.PlayerFile;
import nl.rutgerkok.hammer.World;
import nl.rutgerkok.hammer.anvil.AnvilWorld;
import nl.rutgerkok.hammer.anvil.tag.AnvilFormat;
import nl.rutgerkok.hammer.material.GlobalMaterialMap;
import nl.rutgerkok.hammer.tag.CompoundTag;
import nl.rutgerkok.hammer.util.Progress;
import nl.rutgerkok.hammer.util.Result;
import nl.rutgerkok.hammer.util.Visitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class OldPlayerFileWalkTest {

    private static class TestVisitor implements Visitor<PlayerFile> {
        private int fileCount = 0;

        @Override
        public Result accept(PlayerFile value, Progress progress) {
            CompoundTag tag = value.getTag();
            Assertions.assertTrue(tag.containsKey(AnvilFormat.PlayerTag.INVENTORY), "Tag must contain inventory subtag");
            fileCount++;
            return Result.NO_CHANGES;
        }
    }

    @Test
    void testBasicUsage() throws IOException {
        Path levelDat = GlobalTempResourceManager.getGlobalTempDir().resolve("anvil_1_7_10/level.dat");
        World world = new AnvilWorld(new GlobalMaterialMap(), levelDat);

        TestVisitor visitor = new TestVisitor();
        world.walkPlayerFiles(visitor);

        Assertions.assertEquals(2, visitor.fileCount, "This world has two player data tags, one in its own file, one in the level.dat");
    }

}
