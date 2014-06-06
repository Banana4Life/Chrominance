package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.Widget;

public abstract class KeyEvent extends Event {
    private final Widget target;
    private final int keyCode;

    protected KeyEvent(Widget target, int keyCode) {
        this.target = target;
        this.keyCode = keyCode;
    }

    public Widget getTarget() {
        return target;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
