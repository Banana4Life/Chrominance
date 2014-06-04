package de.cubeisland.games.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class EventProcessor implements EventSender {
    private final Map<Class<? extends Event>, Set<EventHandler<Event, EventSender>>> eventHandlers = new HashMap<>();

    @Override
    public boolean trigger(Event event) {
        return this.trigger(this, event);
    }

    /**
     * Triggers an event
     *
     * @param sender the server
     * @param event the event
     * @return true if there was an applicable handler, otherwise false
     */
    public boolean trigger(EventSender sender, Event event) {
        boolean result = false;
        Set<EventHandler<Event, EventSender>> handlers = this.eventHandlers.get(event.getClass());
        if (handlers != null) {
            for (EventHandler<Event, EventSender> handler : handlers) {
                if (handler.getApplicableSender().isAssignableFrom(sender.getClass())) {
                    handler.handle(sender, event);
                    result = true;
                }
            }
        }
        return result;
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
