package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;

public abstract class KeyEvent implements Event {
    private final int keyCode;

    protected KeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
