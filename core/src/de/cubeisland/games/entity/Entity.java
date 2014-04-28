package de.cubeisland.games.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ComponentHolder;

/**
 * Created by Jonas on 28.04.14.
 */
public class Entity extends ComponentHolder<Entity> {
    private EntityType type;
    private Vector2 location;
    private Vector2 velocity;

    public EntityType getType() {
        return type;
    }

    public Vector2 getLocation() {
        return location;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
