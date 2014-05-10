package de.cubeisland.games.component;

import java.lang.reflect.Constructor;
import java.util.*;

public class ComponentHolder<T extends ComponentHolder<T>> {
    private List<Component<T>> components = new ArrayList<>();
    protected static final Map<Class<? extends Component<?>>, Constructor<? extends Component<?>>> CONSTRUCTOR_CACHE = new HashMap<>();

    public void update(float delta) {
        for (Component<T> component : components) {
            component.update(delta);
        }
    }

    public <C extends Component<T>> C attach(Class<C> componentClass) {
        C component = this.createComponent(componentClass);
        this.components.add(component);
        component.onAttach();
        Collections.sort(this.components);
        return component;
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

    @SuppressWarnings("unchecked")
    protected <C extends Component<T>> C createComponent(Class<C> componentType) {
        Constructor<? extends Component<?>> constructor = CONSTRUCTOR_CACHE.get(componentType);

        if (constructor == null) {
            try {
                constructor = componentType.getConstructor();
                constructor.setAccessible(true);
                CONSTRUCTOR_CACHE.put(componentType, constructor);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Invalid component: No default constructor!", e);
            }
        }

        try {
            C component = (C) constructor.newInstance();
            try {
                component.init((T) this);
            } catch (RuntimeException | Error e) {
                throw new IllegalArgumentException("Invalid component: Error during initialization!", e);
            }
            return component;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Invalid component: Error during construction!");
        }
    }

    public boolean has(Class<Component<T>> componentClass) {
        return this.get(componentClass) != null;
    }
}
