package de.cubeisland.games.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class EventProcessor implements EventSender {
    private final Map<Class<? extends Event>, Set<EventHandler<EventSender, Event>>> eventHandlers = new HashMap<>();

    /**
     * Triggers an event
     *
     * @param sender the server
     * @param event the event
     * @return true if there was an applicable handler, otherwise false
     */
    @Override
    public boolean trigger(EventSender sender, Event event) {
        boolean result = false;
        Set<EventHandler<EventSender, Event>> handlers = this.eventHandlers.get(event.getClass());
        if (handlers != null) {
            for (EventHandler<EventSender, Event> handler : handlers) {
                if (handler.getApplicableSender().isAssignableFrom(sender.getClass())) {
                    handler.handle(sender, event);
                    event.setHandled();
                    result = true;
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void registerEventHandler(EventHandler<? extends EventSender, ? extends Event> handler) {
        Set<EventHandler<EventSender, Event>> handlers = this.eventHandlers.get(handler.getApplicableEvent());
        if (handlers == null) {
            handlers = new HashSet<>(1);
            this.eventHandlers.put(handler.getApplicableEvent(), handlers);
        }
        handlers.add((EventHandler<EventSender, Event>) handler);
    }

    public void unregisterEventHandler(EventHandler<EventSender, Event> handler) {
        Set<EventHandler<EventSender, Event>> handlers = this.eventHandlers.get(handler.getApplicableEvent());
        if (handlers != null) {
            handlers.remove(handler);
        }
    }
}
