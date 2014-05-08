package de.cubeisland.games.component;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ComponentFactory {

    private final Map<Class<? extends Component<?>>, Constructor<? extends Component<?>>> constructorCache;

    public ComponentFactory() {
        this.constructorCache = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <H extends ComponentHolder<H>, C extends Component<H>> C createComponent(H owner, Class<C> componentType) {
        Constructor<? extends Component<?>> constructor = this.constructorCache.get(componentType);

        if (constructor == null) {
            try {
                constructor = componentType.getConstructor();
                constructor.setAccessible(true);
                this.constructorCache.put(componentType, constructor);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Invalid component: No default constructor!", e);
            }
        }

        try {
            C component = (C) constructor.newInstance();
            try {
                component.init(owner);
            } catch (RuntimeException | Error e) {
                throw new IllegalArgumentException("Invalid component: Error during initialization!", e);
            }
            return component;
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Invalid component: Error during construction!");
        }
    }
}
