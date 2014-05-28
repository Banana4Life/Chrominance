package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;
import java.util.*;

public abstract class ResourceManager {
    private String directory;
    private Chrominance game;

    public ResourceManager(Chrominance game, String directory) {
        this.game = game;
        this.directory = directory;

        loadResources();
    }

    private void loadResources() {
        FileHandle baseDir = Gdx.files.internal(this.directory);

        Field[] fields = this.getClass().getFields();
        Map<String, FileHandle> fileMap = getFileMap(baseDir);

        for (Field field : fields) {
            makeResource(field, fileMap);
        }
    }

    protected abstract void makeResource(Field field, Map<String, FileHandle> fileMap);

    private Map<String, FileHandle> getFileMap(FileHandle file) {
        HashMap<String, FileHandle> fileMap = new HashMap<>();
        if (file.isDirectory()) {
            for (FileHandle childFile : file.list()) {
                fileMap.putAll(getFileMap(childFile));
            }
            return fileMap;
        } else {
            fileMap.put(file.nameWithoutExtension(), file);
            return fileMap;
        }
    }

    public Chrominance getGame() {
        return game;
    }
}