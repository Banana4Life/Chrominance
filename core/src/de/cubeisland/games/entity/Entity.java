package de.cubeisland.games.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.level.Level;

import static de.cubeisland.games.util.VectorUtil.zero;

public final class Entity extends ComponentHolder<Entity> {
    private EntityType type;
    private final Level level;
    private Vector2 location = zero();
    private Vector2 velocity = zero();
    private boolean alive = true;
    private boolean initialized = false;

    public Entity(EntityType type, Level level) {
        this.type = type;
        this.level = level;
    }

    public void initialize() {
        if (!this.initialized) {
            this.type.initialize(this);
            this.initialized = true;
        }
    }

    public EntityType getType() {
        return type;
    }

    public Vector2 getLocation() {
        return location;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Entity setLocation(Vector2 location) {
        this.location = location.cpy();
        return this;
    }

    public Entity setVelocity(Vector2 velocity) {
        this.velocity = velocity.cpy();
        return this;
    }


    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }

    boolean isInitialized() {
        return initialized;
    }

    public Level getLevel() {
        return level;
    }
}
