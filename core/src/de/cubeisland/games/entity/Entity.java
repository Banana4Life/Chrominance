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

    public <T extends Component> T attach(Class<T> componentClass) {

        try {
            T component = componentClass.getConstructor().newInstance();
            this.components.add(component);
            return component;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public void detach(Class<? extends Component> componentClass) {
        Component removed = this.get(componentClass);
        if (removed != null)
        {
            this.components.remove(removed);
            removed.onDetach();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T get(Class<T> componentClass) {
        for (Component component : this.components) {
            if (component.getClass() == componentClass) {
                return (T)component;
            }
        }
        return null;
    }

    public boolean has(Class<Component> componentClass) {
        return this.get(componentClass) != null;
    }
}
