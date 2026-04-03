package nl.rutgerkok.hammer;

import org.apache.commons.io.FileUtils;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class GlobalTempResourceManager implements LauncherSessionListener {
    private static Path globalTempDir;

    public static Path getGlobalTempDir() {
        return globalTempDir;
    }

    @Override
    public void launcherSessionOpened(LauncherSession session) {
        try {
            Path src = Paths.get("src", "test", "resources");
            globalTempDir = Files.createTempDirectory("test-resources-");

            if (Files.exists(src)) {
                FileUtils.copyDirectory(src.toFile(), globalTempDir.toFile());
            }
        } catch (Exception e) {
            throw new Error(e);
        }

        System.out.println("[JUnit] Copied test resources to " + globalTempDir);
    }

    @Override
    public void launcherSessionClosed(LauncherSession session) {
        FileUtils.deleteQuietly(globalTempDir.toFile());
    }
}
