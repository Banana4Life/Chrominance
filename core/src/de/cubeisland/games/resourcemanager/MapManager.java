package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.level.Map;

import java.lang.reflect.Field;

public class MapManager extends ResourceManager {
    public Map map1;

    public MapManager() {
        super("./maps");
    }
    @Override
    protected void makeResource(Field field, java.util.Map<String, FileHandle> fileMap) {
        try {
            field.set(this, new Map(fileMap.get(field.getName())));
        } catch (IllegalAccessException e) {
            Gdx.app.log("Error loading textures", e.getMessage());
        }
    }
}
