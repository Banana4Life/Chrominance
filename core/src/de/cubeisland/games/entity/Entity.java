package de.cubeisland.games.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.level.Level;

import java.util.UUID;

import static de.cubeisland.games.util.VectorUtil.zero;

public final class Entity extends ComponentHolder<Entity> {
    private final UUID id;
    private EntityType type;
    private Level level;
    private Vector2 location = zero();
    private Vector2 velocity = zero();
    private boolean alive = true;
    private boolean spawned = false;
    private boolean initialized = false;

    public Entity(UUID id, EntityType type, Level level) {
        this.id = id;
        this.type = type;
        this.level = level;
    }

    public void initialize() {
        if (!this.initialized) {
            this.type.initialize(this);
            this.initialized = true;
        }
    }

    boolean isInitialized() {
        return initialized;
    }

    public void spawned() {
        if (!this.spawned) {
            this.type.spawned(this);
            this.spawned = true;
        }
    }

    public boolean isSpawned() {
        return spawned;
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
        if (velocity != null) {
            this.velocity = velocity.cpy();
        }
        return this;
    }

    public Entity setAngle(float degrees) {
        this.velocity.setAngle(degrees);
        return this;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
        trigger(this, new EntityDeathEvent());
    }

    public Level getLevel() {
        return level;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entity entity = (Entity) o;

        return this.id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public static class EntityDeathEvent extends Event {
    }
}
