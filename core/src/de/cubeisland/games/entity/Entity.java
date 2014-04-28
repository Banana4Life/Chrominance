package de.cubeisland.games.entity;

import de.cubeisland.games.entity.component.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jonas on 28.04.14.
 */
public class Entity {
    private List<Component> components = new CopyOnWriteArrayList<Component>();
    private EntityType type;

    public void update(int delta) {
        for (Component component : components) {
            component.update(delta);
        }
    }

    public Component registerComponents(Class<? extends Component> componentClass) {
        return null;
    }

    public void unregisterComponent(Class<? extends Component> componentClass) {
    }
}
