package de.cubeisland.games.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import de.cubeisland.games.collision.CollisionDetector;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.component.TickPhase;
import de.cubeisland.games.component.level.GridRenderer;
import de.cubeisland.games.component.level.PathRenderer;
import de.cubeisland.games.component.level.WaveController;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.type.Tower;
import de.cubeisland.games.screen.GameScreen;
import de.cubeisland.games.wave.Difficulty;
import de.cubeisland.games.wave.DummyWaveGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Level extends ComponentHolder<Level> implements Disposable {
    private final List<Entity> entities;
    private final List<Entity> spawnQueue;

    private final MapStructure map;
    private final GameScreen screen;

    private final EntityFactory entityFactory;
    private final CollisionDetector collisionDetector;
    private final Difficulty difficulty;

    private float saturation = 0f;

    public Level(GameScreen screen, MapStructure map) {
        this.screen = screen;
        this.entityFactory = new EntityFactory(this);
        this.entities = new ArrayList<>();
        this.spawnQueue = new ArrayList<>();
        this.collisionDetector = new CollisionDetector(this);
        this.difficulty = Difficulty.NORMAL;

        this.attach(PathRenderer.class);
        this.attach(GridRenderer.class);
        this.attach(WaveController.class)
                .setGenerator(new DummyWaveGenerator(this.difficulty))
                .setDifficulty(Difficulty.EASY);

        this.map = map;
        this.spawnTowers();
    }

    public float getSaturation() {
        return saturation;
    }
    public void setSaturation(float saturation) {
        this.saturation = saturation;
        if (this.saturation < 0f) this.saturation = 0;
        if (this.saturation > 1f) this.saturation = 1;
    }
    public void subSaturation(float amount) {
        this.saturation += amount;
        if (saturation < 0f) saturation = 0;
        if (saturation > 1f) saturation = 1;
    }

    public List<Entity> getEntities() {
        List<Entity> copy = new ArrayList<>(this.entities);
        copy.addAll(this.spawnQueue);
        return copy;
    }

    private void spawnTowers() {
        for (Map.Entry<Vector2, Tower> entry : this.map.getTowerLocations().entrySet()) {
            this.spawn(entry.getValue(), getMap().offset(getMap().scale(entry.getKey())));
        }
    }

    public Entity spawn(Entity e, Vector2 location) {
        e.setLocation(location);
        e.initialize();
        this.spawnQueue.add(e);
        return e;
    }

    public Entity spawn(EntityType type, Vector2 location) {
        Entity e = this.entityFactory.createEntity(type);
        return this.spawn(e, location);
    }

    public void update(float delta) {
        if (this.get(WaveController.class).hasFinished()) {
            screen.won();
        }
        if (this.saturation >= 1) {
            screen.lost();
        }

        for (TickPhase phase : TickPhase.values()) {
            switch (phase) {
                case POST_COLLISION:
                    collisionDetector.collide();
            }
            this.update(phase, delta);
        }

        Iterator<Entity> it = this.entities.iterator();
        Entity e;
        while (it.hasNext()) {
            e = it.next();
            if (!e.isAlive()) {
                it.remove();
                e.dispose();
            }
        }
    }

    @Override
    public void update(TickPhase tickPhase, float delta) {
        this.updateLevel(tickPhase, delta);
        this.updateEntities(tickPhase, delta);
    }

    private void updateLevel(TickPhase tickPhase, float delta) {
        super.update(tickPhase, delta);
    }

    private void updateEntities(TickPhase tickPhase, float delta) {
        this.entities.addAll(this.spawnQueue);
        this.spawnQueue.clear();

        for (Entity entity : this.entities) {
            entity.update(tickPhase, delta);
        }
    }

    public MapStructure getMap()
    {
        return map;
    }

    public EntityFactory getEntityFactory()
    {
        return entityFactory;
    }

    public GameScreen getScreen() {
        return screen;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void dispose() {
        getScreen().getGame().getInput().clear();
    }
}
