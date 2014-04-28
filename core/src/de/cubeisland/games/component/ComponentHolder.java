package de.cubeisland.games.component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by phill_000 on 28.04.2014.
 */
public class ComponentHolder<T> {
    private List<Component<T>> components = new CopyOnWriteArrayList<Component<T>>();


    public void update(int delta) {
        for (Component component : components) {
            component.update(delta);
        }
    }

    public <C extends Component<T>> C attach(Class<C> componentClass) {

        try {
            C component = componentClass.getConstructor().newInstance();
            this.components.add(component);
            return component;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public void detach(Class<? extends Component<T>> componentClass) {
        Component<T> removed = this.get(componentClass);
        if (removed != null)
        {
            this.components.remove(removed);
            removed.onDetach();
        }
    }

    @SuppressWarnings("unchecked")
    public <C extends Component<T>> C get(Class<C> componentClass) {
        for (Component component : this.components) {
            if (component.getClass() == componentClass) {
                return (C)component;
            }
        }
        return null;
    }

    public boolean has(Class<Component<T>> componentClass) {
        return this.get(componentClass) != null;
    }
}
