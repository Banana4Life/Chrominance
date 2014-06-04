package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;

public class KeyTypedEvent implements Event {
    private final char character;

    public KeyTypedEvent(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
