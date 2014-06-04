package de.cubeisland.games.ui.event;

import de.cubeisland.games.ui.Widget;

public class KeyDownEvent extends KeyEvent {
    public KeyDownEvent(Widget target, int keyCode) {
        super(target, keyCode);
    }
}
