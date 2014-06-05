package de.cubeisland.games.event;

public interface EventHandler<S extends EventSender, E extends Event> {
    Class<S> getApplicableSender();
    Class<E> getApplicableEvent();
    void handle(S sender, E event);
}
