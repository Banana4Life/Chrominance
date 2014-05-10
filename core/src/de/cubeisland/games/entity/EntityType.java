package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.entity.GarbageCollector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class EntityType {
    private final List<Class<? extends Component<Entity>>> components = new CopyOnWriteArrayList<>();

    {
        this.add(GarbageCollector.class);
    }

    protected final void add(Class<? extends Component<Entity>> component) {
        this.components.add(component);
    }

    public List<Class<? extends Component<Entity>>> getComponents() {
        return components;
    }
}
