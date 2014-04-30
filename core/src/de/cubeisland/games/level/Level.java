package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.level.tile.TileType;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level extends ComponentHolder<Level> {
    private final List<Entity> entities;
    private final TileType[][] mapData;
    private EntityFactory entityFactory;

    public Level() {
        this.entityFactory = new EntityFactory();
        this.entities = new CopyOnWriteArrayList<>();

        this.mapData = loadMap(new Pixmap(Gdx.files.internal("map.bmp")));
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    private TileType[][] loadMap(Pixmap rawMap) {
        TileType[][] dummyMap = new TileType[rawMap.getWidth()][rawMap.getHeight()];
        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                dummyMap[x][y] = TileType.getType(rawMap.getPixel(x, y));
            }
        }

        return dummyMap;
    }

    public Entity spawn(EntityType type) {
        Entity e = this.entityFactory.createEntity(type);
        this.entities.add(e);
        return e;
    }
}
