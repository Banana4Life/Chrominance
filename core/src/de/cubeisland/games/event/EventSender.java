package de.cubeisland.games.event;

/**
 * Created by jonas on 26.05.14.
 */
public interface EventSender {
    void trigger(EventSender sender, Event event);
}
