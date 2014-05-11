package de.cubeisland.games.component;

import de.cubeisland.games.component.event.Event;
import de.cubeisland.games.component.event.EventHandler;

import java.lang.reflect.Constructor;
import java.util.*;

public class ComponentHolder<T extends ComponentHolder<T>> {
    private final List<Component<T>> components = new ArrayList<>();
    private final Map<Class<? extends Event>, Set<EventHandler<Event>>> eventHandlers = new HashMap<>();
    protected static final Map<Class<? extends Component<?>>, Constructor<? extends Component<?>>> CONSTRUCTOR_CACHE = new HashMap<>();
    private static final ComponentComparator COMPARATOR = new ComponentComparator();

    public void emit(Component sender, Event event) {
        Set<EventHandler<Event>> handlers = this.eventHandlers.get(event.getClass());
        if (handlers != null) {
            for (EventHandler<Event> handler : handlers) {
                handler.handle(sender, event);
            }
        }
    }

    public void registerEventHandler(EventHandler<Event> handler) {
        Set<EventHandler<Event>> handlers = this.eventHandlers.get(handler.getApplicableType());
        if (handlers == null) {
            handlers = new HashSet<>(1);
            this.eventHandlers.put(handler.getApplicableType(), handlers);
        }
        handlers.add(handler);
    }

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
