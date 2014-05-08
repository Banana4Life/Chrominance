package de.cubeisland.games.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentFactory;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityType;
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
    private EntityFactory entityFactory;
    private final WaveGenerator waveGenerator;
    private Wave currentWave;
    private final List<Path> paths;

    public Level(FileHandle fileHandle) {
        this.entityFactory = new EntityFactory(new ComponentFactory());
        this.entities = new CopyOnWriteArrayList<>();

        mapData = loadMap(new Pixmap(fileHandle));
        this.waveGenerator = new DummyWaveGenerator();

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(new Vector2(0, 0)));
        nodes.add(new Node(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));

        this.paths = new ArrayList<>();
        this.paths.add(new Path(nodes));
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
                dummyMap[x][y] = TileType.getType(rawMap.getPixel(x, y));
            }
        }

        return dummyMap;
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

        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.line(paths.get(0).getSpawn().getLocation(), paths.get(0).getTarget().getLocation());
        sr.end();

        for (Entity entity : this.entities) {
            entity.update(delta);
        }
        super.update(delta);
    }
}
