package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class SoundManager extends ResourceManager<Sound> {

    public Sound pew;

    public SoundManager() {
        super("sound");
    }

    @Override
    protected Sound makeResource(FileHandle basedir, Field field) {
        return Gdx.audio.newSound(basedir.child(fieldToPath(field) + ".wav"));
    }
}
