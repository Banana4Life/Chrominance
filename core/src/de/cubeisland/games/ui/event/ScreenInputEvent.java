package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;

public class ScreenInputEvent extends Event {
    private final int x;
    private final int y;

    public ScreenInputEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
