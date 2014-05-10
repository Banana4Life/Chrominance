package de.cubeisland.games.component.event;

import de.cubeisland.games.component.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodEventHandler implements EventHandler<Event> {

    private final Object holder;
    private final Method method;
    private final Class<Event> applicableType;

    public MethodEventHandler(Object holder, Method method, Class<Event> applicableType) {
        this.holder = holder;
        this.method = method;
        this.applicableType = applicableType;
    }

    @Override
    public Class<Event> getApplicableType() {
        return this.applicableType;
    }

    @Override
    public void handle(Component sender, Event event) {
        try {
            this.method.invoke(this.holder, sender, event);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace(System.err);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<EventHandler<Event>> parseHandlers(Object o) {
        final List<EventHandler<Event>> handlers = new ArrayList<>();
        for (Method method : o.getClass().getMethods()) {
            if ("handle".equals(method.getName()) && method.getReturnType() == void.class) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 2 && Component.class.isAssignableFrom(paramTypes[0]) && Event.class.isAssignableFrom(paramTypes[1])) {
                    method.setAccessible(true);
                    handlers.add(new MethodEventHandler(o, method, (Class<Event>) paramTypes[1]));
                }
            }
        }
        return handlers;
    }
}
