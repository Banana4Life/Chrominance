package de.cubeisland.games.resourcemanager;

import de.cubeisland.games.Chrominance;
import de.cubeisland.games.level.Map;

import java.lang.reflect.Field;

public class MapManager extends ResourceManager<Map> {
    public Map map1;

    public MapManager(Chrominance game) {
        super(game, "./maps");
    }

    @Override
    protected Map makeResource(Field field, FileHandles fileMap) {
        return new Map(fileMap.get(field.getName()));
    }
}
