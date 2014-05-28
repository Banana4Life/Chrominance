package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;
import java.util.Map;

public class TextureManager extends ResourceManager {
    public Texture badlogic;

    public TextureManager(Chrominance game) {
        super(game, "./textures");
    }

    @Override
    protected void makeResource(Field field, Map<String, FileHandle> fileMap) {
        try {
            field.set(this, new Texture(fileMap.get(field.getName())));
        } catch (IllegalAccessException e) {
            Gdx.app.log("Error loading textures", e.getMessage());
        }
    }
}