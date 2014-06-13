package de.cubeisland.games.event;

public interface EventSender {
    boolean trigger(EventSender sender, Event event);
}
