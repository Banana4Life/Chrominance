package de.cubeisland.games.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponentHolder<T extends ComponentHolder> {
    private List<Component<T>> components = new ArrayList<>();

    public void update(float delta) {
        for (int i = 0; i < components.size(); ++i) {
            components.get(i).update(delta);
        }
    }

    public void attach(Component<T> component) {
        this.components.add(component);
        component.onAttach();
        Collections.sort(this.components);
    }

    public void detach(Class<? extends Component<T>> componentClass) {
        Component<T> removed = this.get(componentClass);
        if (removed != null) {
            this.components.remove(removed);
            removed.onDetach();
        }
    }

    @SuppressWarnings("unchecked")
    public <C extends Component<T>> C get(Class<C> componentClass) {
        for (Component component : this.components) {
            if (component.getClass() == componentClass) {
                return (C) component;
            }
        }
        return null;
    }

    public boolean has(Class<Component<T>> componentClass) {
        return this.get(componentClass) != null;
    }
}
