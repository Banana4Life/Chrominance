package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.entity.Entity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by phill_000 on 28.04.2014.
 */
public class Level extends ComponentHolder<Level> {
    private final List<Entity> entities;
    private final int[][] mapData;

    public Level() {
        entities = new CopyOnWriteArrayList<Entity>();

        mapData = convertMap(new Pixmap(Gdx.files.internal("map.bmp")));
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    private int[][] convertMap(Pixmap rawMap) {
        int[][] dummyMap = new int[rawMap.getWidth()][rawMap.getHeight()];
        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                dummyMap[x][y] = rawMap.getPixel(x, y);
            }
        }

        return dummyMap;
    }
}
