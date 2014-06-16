package de.cubeisland.games.entity;

import de.cubeisland.games.collision.CollisionSourceHandler;
import de.cubeisland.games.collision.CollisionTargetHandler;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.component.entity.GarbageCollector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class EntityType {
    private final List<Class<? extends Component<Entity>>> components = new CopyOnWriteArrayList<>();

    protected EntityType() {
        add(GarbageCollector.class);

        if (this instanceof CollisionSourceHandler) {
            add(Collider.class);
        }
        if (this instanceof CollisionTargetHandler) {
            add(Collidable.class);
        }
    }

    protected final void add(Class<? extends Component<Entity>> component) {
        this.components.add(component);
    }

    public List<Class<? extends Component<Entity>>> getComponents() {
        return components;
    }

    final void initialize(Entity e) {
        if (e.getType() != this) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " can only initialize entities of that type!");
        }
        if (!e.isInitialized()) {
            this.onInitialize(e);
        }
    }

    protected void onInitialize(Entity e) {
    }

    final void spawned(Entity e) {
        if (e.getType() != this) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " can only initialize entities of that type!");
        }
        if (!e.isSpawned()) {
            this.onSpawned(e);
        }
    }

    protected void onSpawned(Entity e) {
    }
}
