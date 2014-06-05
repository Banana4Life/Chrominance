package de.cubeisland.games.component;

import com.badlogic.gdx.utils.Disposable;
import de.cubeisland.games.event.EventProcessor;

import java.lang.reflect.Constructor;
import java.util.*;

public class ComponentHolder<T extends ComponentHolder<T>> extends EventProcessor implements Disposable {
    private final List<Component<T>> components = new ArrayList<>();
    protected static final Map<Class<? extends Component<?>>, Constructor<? extends Component<?>>> CONSTRUCTOR_CACHE = new HashMap<>();
    private static final ComponentComparator COMPARATOR = new ComponentComparator();

    public void update(TickPhase phase, float delta) {
        for (Component<T> component : components) {
            if (component.shouldTick(phase)) {
                component.update(delta);
            }
        }
    }

    public <C extends Component<T>> C attach(Class<C> componentClass) {
        C component = this.createComponent(componentClass);
        this.components.add(component);
        component.onAttach();
        Collections.sort(this.components, COMPARATOR);
        return component;
    }

    @SuppressWarnings("unchecked")
    public void detach(Class<? extends Component> componentClass) {
        Component<T> removed = this.get(componentClass);
        if (removed != null) {
            this.components.remove(removed);
            removed.onDetach();
        }
    }

    public void detachAll() {
        for (Component<T> component : new ArrayList<>(this.components)) {
            detach(component.getClass());
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
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Invalid component: Error during initialization!", e);
            }
            return component;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Invalid component: Error during construction!", e);
        }
    }

    public <C extends Component<T>> boolean has(Class<C> componentClass) {
        return this.get(componentClass) != null;
    }

    @Override
    public void dispose() {
        this.detachAll();
    }

    private static final class ComponentComparator implements Comparator<Component<?>> {
        @Override
        public int compare(Component<?> a, Component<?> b) {
            if (a.getBefore() == b.getClass() || b.getAfter() == a.getClass()) {
                return -1;
            } else if (a.getAfter() == b.getClass() || b.getBefore() == a.getClass()) {
                return 1;
            }
            return 0;
        }
    }
}
