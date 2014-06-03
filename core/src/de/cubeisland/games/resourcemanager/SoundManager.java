package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import de.cubeisland.games.Chrominance;

import java.lang.reflect.Field;

public class SoundManager extends ResourceManager<Sound> {

    public Sound pew;

    public SoundManager(Chrominance game) {
        super(game, "./sound");
    }

    @Override
    protected Sound makeResource(Field field, FileHandles fileMap) {
        return Gdx.audio.newSound(fileMap.get(field.getName()));
    }
}
