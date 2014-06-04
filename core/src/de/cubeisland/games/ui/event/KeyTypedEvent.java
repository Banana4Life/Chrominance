package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.Widget;

public class KeyTypedEvent implements Event {
    private final char character;

    public KeyTypedEvent(Widget target, char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
