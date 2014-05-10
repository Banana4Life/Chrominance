package de.cubeisland.games.component.event;

import de.cubeisland.games.component.Component;

public interface EventHandler<T extends Event> {
    Class<T> getApplicableType();
    void handle(Component sender, T event);
}
