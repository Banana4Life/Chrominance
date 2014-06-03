package de.cubeisland.games.event;

public interface EventHandler<E extends Event, S extends EventSender> {
    Class<E> getApplicableEvent();
    Class<S> getApplicableSender();
    void handle(S sender, E event);
}
