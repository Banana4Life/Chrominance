package de.cubeisland.games.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.cubeisland.engine.logging.DefaultLogFactory;
import de.cubeisland.engine.logging.Log;
import de.cubeisland.engine.logging.LogFactory;
import de.cubeisland.engine.logging.LogLevel;
import de.cubeisland.engine.logging.target.PrintTarget;
import de.cubeisland.engine.logging.target.file.AsyncFileTarget;
import de.cubeisland.engine.logging.target.file.format.LogFileFormat;
import de.cubeisland.engine.logging.util.LoggingOutputStream;
import de.cubeisland.games.Chrominance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.badlogic.gdx.Files.FileType.Internal;

public class DesktopLauncher {
    public static void main (String[] arg) {

        LogFactory factory = new DefaultLogFactory();

        final Log log = factory.getLog(DesktopLauncher.class);
        log.addTarget(PrintTarget.STDOUT);

        try {
            Path path = Files.createTempDirectory(Chrominance.class.getSimpleName()).resolve("application.log");

            AsyncFileTarget fileTarget = new AsyncFileTarget(path.toFile(), new LogFileFormat());
            log.addTarget(fileTarget);

            log.info("Started logging to {} ...", path);
        } catch (IOException e) {
            log.error("Failed to start the file logging!", e);
        }

        LoggingOutputStream.hijackStandardOutput(log, LogLevel.INFO);
        LoggingOutputStream.hijackStandardError(log, LogLevel.ERROR);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                log.shutdown();
            }
        }));

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.samples = 0;
        config.resizable = false;
        config.vSyncEnabled = false;
        config.foregroundFPS = 300;
        config.backgroundFPS = 70;
        config.addIcon("icon.png", Internal);
        new LwjglApplication(new Chrominance(), config);
    }
}
