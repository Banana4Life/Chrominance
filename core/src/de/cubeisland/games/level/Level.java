package de.cubeisland.games.level;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.component.level.GridRenderer;
import de.cubeisland.games.component.level.PathRenderer;
import de.cubeisland.games.component.level.WaveController;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.wave.Difficulty;
import de.cubeisland.games.wave.DummyWaveGenerator;
import de.cubeisland.games.wave.Wave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Level extends ComponentHolder<Level> {
    private final List<Entity> entities;

    private final Map map;

    private EntityFactory entityFactory;
    private Wave currentWave;

    public Level(FileHandle fileHandle) {
        this.entityFactory = new EntityFactory();
        this.entities = new ArrayList<>();

        this.attach(PathRenderer.class);
        this.attach(GridRenderer.class);
        this.attach(WaveController.class)
                .setGenerator(new DummyWaveGenerator())
                .setDifficulty(Difficulty.EASY);

        this.map = Map.load(fileHandle);
        this.spawnTowers();
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    private void spawnTowers() {
        for (Vector2 loc : this.map.getTowerLocations()) {
            this.spawn(EntityTypes.TOWER, getMap().scale(loc));
        }
    }

    public Entity spawn(Entity e, Vector2 location) {
        e.setLocation(location);
        this.entities.add(e);
        return e;
    }

    public Entity spawn(EntityType type, Vector2 location) {
        Entity e = this.entityFactory.createEntity(type);
        return this.spawn(e, location);
    }

    @Override
    public void update(float delta) {
        Entity e;
        Iterator<Entity> it = this.entities.iterator();
        while (it.hasNext()) {
            e = it.next();
            e.update(delta);
            if (!e.isAlive()) {
                it.remove();
            }
        }
        super.update(delta);
    }

    public Map getMap()
    {
        return map;
    }

    public EntityFactory getEntityFactory()
    {
        return entityFactory;
    }
}
