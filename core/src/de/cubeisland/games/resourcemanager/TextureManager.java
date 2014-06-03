package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.graphics.Texture;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class TextureManager extends ResourceManager<Texture> {
    public Texture badlogic;

    public TextureManager(Chrominance game) {
        super(game, "./textures");
    }

    @Override
    protected Texture makeResource(Field field, FileHandles fileMap) {
        return new Texture(fileMap.get(field.getName()));
    }
}