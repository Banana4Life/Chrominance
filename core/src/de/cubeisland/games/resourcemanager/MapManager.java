package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.TileMapWithPathsAndTowerLocations;

import java.lang.reflect.Field;

public class MapManager extends ResourceManager<TileMapWithPathsAndTowerLocations> {
    public TileMapWithPathsAndTowerLocations map1;
    public TileMapWithPathsAndTowerLocations map2;

    public MapManager() {
        super("maps");
    }

    @Override
    protected TileMapWithPathsAndTowerLocations makeResource(FileHandle basedir, Field field) {
        return new TileMapWithPathsAndTowerLocations(basedir.child(fieldToPath(field) + ".bmp"));
    }
}
