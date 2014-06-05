package de.cubeisland.games.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodEventHandler implements EventHandler<EventSender, Event> {

    private final Object holder;
    private final Method method;
    private final Class<Event> applicableEvent;
    private final Class<EventSender> applicableSender;

    public MethodEventHandler(Object holder, Method method, Class<Event> applicableEvent, Class<EventSender> applicableSender) {
        this.holder = holder;
        this.method = method;
        this.applicableEvent = applicableEvent;
        this.applicableSender = applicableSender;
    }

    @Override
    public Class<Event> getApplicableEvent() {
        return this.applicableEvent;
    }

    @Override
    public Class<EventSender> getApplicableSender() {
        return this.applicableSender;
    }

    @Override
    public void handle(EventSender sender, Event event) {
        try {
            this.method.invoke(this.holder, sender, event);
        } catch (ReflectiveOperationException e) {
            throw new BrokenEventHandlerException("Failed to invoke a event handler!", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<EventHandler<EventSender, Event>> parseHandlers(Object o) {
        final List<EventHandler<EventSender, Event>> handlers = new ArrayList<>();
        for (Method method : o.getClass().getMethods()) {
            if ("handle".equals(method.getName()) && method.getReturnType() == void.class) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 2 && EventSender.class.isAssignableFrom(paramTypes[0]) && Event.class.isAssignableFrom(paramTypes[1])) {
                    method.setAccessible(true);
                    handlers.add(new MethodEventHandler(o, method, (Class<Event>) paramTypes[1], (Class<EventSender>) paramTypes[0]));
                }
            }
        }
        return handlers;
    }
}
