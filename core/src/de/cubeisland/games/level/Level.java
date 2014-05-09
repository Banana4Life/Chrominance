package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentFactory;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.component.level.PathRenderer;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.level.tile.TileType;
import de.cubeisland.games.wave.Difficulty;
import de.cubeisland.games.wave.DummyWaveGenerator;
import de.cubeisland.games.wave.Wave;
import de.cubeisland.games.wave.WaveGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Level extends ComponentHolder<Level> {
    private final List<Entity> entities;

    private final TileType[][] mapData;
    private final ComponentFactory componentFactory;

    private EntityFactory entityFactory;
    private final WaveGenerator waveGenerator;
    private Wave currentWave;
    private final List<Path> paths;

    public Level(FileHandle fileHandle) {
        this.componentFactory = new ComponentFactory();
        this.entityFactory = new EntityFactory(componentFactory);
        this.entities = new CopyOnWriteArrayList<>();

        this.attach(componentFactory.createComponent(this, PathRenderer.class));

        paths = new ArrayList<>();

        mapData = loadMap(new Pixmap(fileHandle));
        this.waveGenerator = new DummyWaveGenerator();
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    public TileType getMapData(int x, int y) {
        return mapData[x][y];
    }

    private TileType[][] loadMap(Pixmap rawMap) {
        TileType[][] dummyMap = new TileType[rawMap.getWidth()][rawMap.getHeight()];

        for (int x = 0; x < rawMap.getWidth(); x++) {
            for (int y = 0; y < rawMap.getHeight(); y++) {
                TileType tileType = TileType.getByColorValue(rawMap.getPixel(x, y));
                dummyMap[x][y] = tileType;
                if (tileType == TileType.TOWER_SLOT) {
                    float scale = Gdx.graphics.getHeight() / dummyMap[0].length;
                    entities.add(entityFactory.createEntity(EntityTypes.TOWER).setLocation(new Vector2((x + 0.5f) * scale, (dummyMap[0].length - 0.5f - y) * scale)));
                }
            }
        }

        for (int x = 0; x < dummyMap.length; x++) {
            for (int y = 0; y < dummyMap[0].length; y++) {
                if (dummyMap[x][y] == TileType.END_PATH) {
                    getPath(dummyMap, x, y);
                }
            }
        }

        return dummyMap;
    }

    private void getPath(TileType[][] map, int x, int y) {
        getPath(map, x, y, new Vector2(), new Vector2(), new ArrayList<Node>());
    }
    private void getPath(TileType[][] map, int x, int y, Vector2 currMove, Vector2 lastMove, List<Node> nodeList) {
        if (currMove.hasOppositeDirection(lastMove)) {
            return;
        }

        if (!(new Rectangle(0, 0, map.length - 1, map[0].length - 1).contains(x, y))) {
            return;
        }

        if (map[x][y] == TileType.PATH && !currMove.hasSameDirection(lastMove)) {
            nodeList.add(new Node(new Vector2(x + 0.5f + currMove.cpy().rotate(180f).x, map[0].length - (y + 0.5f) + currMove.y)));
        }
        if (map[x][y] == TileType.BEGIN_PATH) {
            nodeList.add(new Node(new Vector2(x + 0.5f, map[0].length - (y + 0.5f))));

            Collections.reverse(nodeList);
            paths.add(new Path(nodeList, (float) Gdx.graphics.getHeight() / (float) map[0].length));

            return;
        } else if (map[x][y] == TileType.END_PATH) {
            nodeList.add(new Node(new Vector2(x + 0.5f, map[0].length - (y + 0.5f))));
        }

        if (map[x][y] == TileType.PATH) {
            getPath(map, x + 1, y, new Vector2(1, 0), currMove, new ArrayList<>(nodeList));
            getPath(map, x - 1, y, new Vector2(-1, 0), currMove, new ArrayList<>(nodeList));
            getPath(map, x, y + 1, new Vector2(0, 1), currMove, new ArrayList<>(nodeList));
            getPath(map, x, y - 1, new Vector2(0, -1), currMove, new ArrayList<>(nodeList));
        } else if (map[x][y] == TileType.END_PATH) {
            getPath(map, x + 1, y, new Vector2(1, 0) , new Vector2(1, 0) , new ArrayList<>(nodeList));
            getPath(map, x - 1, y, new Vector2(-1, 0), new Vector2(-1, 0), new ArrayList<>(nodeList));
            getPath(map, x, y + 1, new Vector2(0, 1) , new Vector2(0, 1) , new ArrayList<>(nodeList));
            getPath(map, x, y - 1, new Vector2(0, -1), new Vector2(0, -1), new ArrayList<>(nodeList));
        }
    }

    protected void spawn(Entity e) {
        Path p = this.paths.get(0);
        Node spawn = p.getSpawn();
        Node target = p.getTarget();

        Vector2 v = target.getLocation().cpy().sub(spawn.getLocation()).scl(1f/20);
        e.setLocation(spawn.getLocation().cpy()).setVelocity(v);

        this.entities.add(e);
    }

    public Entity spawn(EntityType type) {
        Entity e = this.entityFactory.createEntity(type);
        this.spawn(e);
        return e;
    }

    @Override
    public void update(float delta) {
        if (this.currentWave == null || this.currentWave.isCompleted()) {
            int num = this.currentWave == null ? 0 : this.currentWave.getNumber();
            this.currentWave = this.waveGenerator.generate(this.entityFactory, num + 1, Difficulty.EASY);
            for (Entity e : this.currentWave.getEntities()) {
                this.spawn(e);
            }
        }

        // render paths //

        // end //

        for (Entity entity : this.entities) {
            entity.update(delta);
        }
        super.update(delta);
    }

    public List<Path> getPaths()
    {
        return paths;
    }
}
