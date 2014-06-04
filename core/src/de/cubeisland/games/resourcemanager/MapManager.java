package de.cubeisland.games.resourcemanager;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.TileMapWithPathsAndTowerLocations;

import java.lang.reflect.Field;

public class MapManager extends ResourceManager<TileMapWithPathsAndTowerLocations> {
    public TileMapWithPathsAndTowerLocations map1;

    public MapManager(Chrominance game) {
        super(game, "./maps");
    }

    @Override
    protected TileMapWithPathsAndTowerLocations makeResource(Field field, FileHandles fileMap) {
        return new TileMapWithPathsAndTowerLocations(fileMap.get(field.getName()));
    }
}
