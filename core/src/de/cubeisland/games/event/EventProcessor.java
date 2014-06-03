package de.cubeisland.games.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class EventProcessor implements EventSender {
    private final Map<Class<? extends Event>, Set<EventHandler<Event, EventSender>>> eventHandlers = new HashMap<>();

    @Override
    public void trigger(Event event) {
        this.trigger(this, event);
    }

    public void trigger(EventSender sender, Event event) {
        Set<EventHandler<Event, EventSender>> handlers = this.eventHandlers.get(event.getClass());
        if (handlers != null) {
            for (EventHandler<Event, EventSender> handler : handlers) {
                if (handler.getApplicableSender().isAssignableFrom(sender.getClass())) {
                    handler.handle(sender, event);
                }
            }
        }
    }

    public void registerEventHandler(EventHandler<Event, EventSender> handler) {
        Set<EventHandler<Event, EventSender>> handlers = this.eventHandlers.get(handler.getApplicableEvent());
        if (handlers == null) {
            handlers = new HashSet<>(1);
            this.eventHandlers.put(handler.getApplicableEvent(), handlers);
        }
        handlers.add(handler);
    }
    public void unregisterEventHandler(EventHandler<Event, EventSender> handler) {
        Set<EventHandler<Event, EventSender>> handlers = this.eventHandlers.get(handler.getApplicableEvent());
        if (handlers != null) {
            handlers.remove(handler);
        }
    }
}
