package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Jonas on 20.05.2014.
 */
public class TextureManager extends ResourceManager<Texture> {
    public Texture badlogic;

    @Override
    protected Texture loadFile(FileHandle file) {
        return new Texture(file);
    }
}
