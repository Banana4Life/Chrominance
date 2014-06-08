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

public class DesktopLauncher {
    public static void main (String[] arg) {

        LogFactory factory = new DefaultLogFactory();

        Log log = factory.getLog(DesktopLauncher.class);
        log.addTarget(PrintTarget.STDOUT);

        AsyncFileTarget fileTarget = new AsyncFileTarget(new File(Chrominance.class.getSimpleName() + ".log"), new LogFileFormat());
        log.addTarget(fileTarget);

        LoggingOutputStream.hijackStandardOutput(log, LogLevel.INFO);
        LoggingOutputStream.hijackStandardError(log, LogLevel.ERROR);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.samples = 0;
        config.resizable = false;
        new LwjglApplication(new Chrominance(log), config);
    }
}
