package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class ResourceManager<T> {
    private String directory;
    private Chrominance game;
    private final Class<T> type;
    private final List<T> resources;

    @SuppressWarnings("unchecked")
    public ResourceManager(Chrominance game, String directory) {
        this.game = game;
        this.directory = directory;

        Type t = this.getClass().getGenericSuperclass();
        Type param = ((ParameterizedType) t).getActualTypeArguments()[0];
        if (!(param instanceof Class)) {
            throw new IllegalArgumentException("Field to detect our type!");
        }
        this.type = (Class<T>) param;
        this.resources = new ArrayList<>();

        loadResources();
    }

    public List<T> getResources() {
        return Collections.unmodifiableList(this.resources);
    }

    private void loadResources() {
        FileHandle baseDir = Gdx.files.internal(this.directory);

        Field[] fields = this.getClass().getFields();
        FileHandles fileMap = getFileMap(baseDir);

        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) && this.type.isAssignableFrom(field.getType())) {
                try {
                    T resource = makeResource(field, fileMap);
                    field.set(this, resource);
                    this.resources.add(resource);
                } catch (IllegalAccessException | RuntimeException e) {
                    Gdx.app.error("resource", "Failed to load resource!", e);
                }
            }
        }
    }

    protected abstract T makeResource(Field field, FileHandles fileMap);

    private FileHandles getFileMap(FileHandle file) {
        FileHandles fileMap = new FileHandles();
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

    protected static final class FileHandles extends HashMap<String, FileHandle> {
        @Override
        public FileHandle get(Object key) {
            FileHandle handle = super.get(key);
            if (handle == null) {
                throw new MissingResourceException(String.valueOf(key));
            }
            return handle;
        }

        public FileHandle get(String mainKey, String fallbackKey) {
            FileHandle handle = super.get(mainKey);
            if (handle == null) {
                handle = super.get(fallbackKey);
            }
            if (handle == null) {
                throw new MissingResourceException(mainKey + " and " + fallbackKey);
            }
            return handle;
        }
    }

    public static class MissingResourceException extends RuntimeException {
        public MissingResourceException(String message) {
            super(message);
        }
    }
}