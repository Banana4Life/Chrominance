package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.lang.reflect.Field;

public abstract class ResourceManager<T> {
    private final String basePath = ".";

    public ResourceManager() {
        loadResources();
    }

    private void loadResources() {
        FileHandle basePath = Gdx.files.internal(this.basePath);

        Field[] fields = this.getClass().getFields();

        for (Field field : fields) {
            loadResource(field, basePath);
        }
    }
    private void loadResource(Field field, FileHandle file) {
        if (file.isDirectory()) {
            FileHandle[] childFiles = file.list();

            for (FileHandle childFile : childFiles) {
                loadResource(field, childFile);
            }
        } else if(file.nameWithoutExtension().equals(field.getName())) {
            try {
                field.set(this, loadFile(file));
            } catch (IllegalAccessException e) {
                Gdx.app.log("Error loading files", e.getMessage());
            }
        }
    }
    protected abstract T loadFile(FileHandle file);
}