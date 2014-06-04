package de.cubeisland.games.ui.event;

import de.cubeisland.games.ui.Widget;

public class KeyUpEvent extends KeyEvent {
    public KeyUpEvent(Widget target, int keyCode) {
        super(target, keyCode);
    }
}
