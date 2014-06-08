package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.level.MapStructure;
import de.cubeisland.games.resource.ResourceBag;

import java.lang.reflect.Field;

public class Maps extends ResourceBag<MapStructure> {
    public MapStructure map1;
    public MapStructure map2;

    private final Towers towers;

    public Maps(Towers towers) {
        this.towers = towers;
    }

    @Override
    protected MapStructure load(FileHandle basedir, Field field) {
        return new MapStructure(this.towers.getResources(), basedir.child(fieldToPath(field) + ".bmp"));
    }
}
