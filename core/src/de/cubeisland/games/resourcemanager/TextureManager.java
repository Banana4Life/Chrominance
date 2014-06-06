package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class TextureManager extends ResourceManager<Texture> {
    public Texture badlogic;

    public TextureManager() {
        super("textures");
    }

    @Override
    protected Texture makeResource(FileHandle basedir, Field field) {
        return new Texture(basedir.child(fieldToPath(field) + ".jpg"));
    }
}