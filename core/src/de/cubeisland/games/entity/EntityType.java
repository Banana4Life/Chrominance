package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Render;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by Jonas on 28.04.14.
 */
public enum EntityType {
    TOWER(asList(Render.class, Render.class, Render.class)),
    NONE;

    private final List<Class<? extends Component<Entity>>> components;

    EntityType(List<Class<? extends Component<Entity>>> components) {
        this.components = components;
    }

    public List<Class<? extends Component<Entity>>> getComponents() {
        return components;
    }
}
