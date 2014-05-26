package de.cubeisland.games.event;

import de.cubeisland.games.component.Component;

public interface EventHandler<T extends Event> {
    Class<T> getApplicableType();
    void handle(EventSender sender, T event);
}
