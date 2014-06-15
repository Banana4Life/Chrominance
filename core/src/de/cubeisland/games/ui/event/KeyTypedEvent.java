package de.cubeisland.games.ui.event;

import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.Input.Keys;

public class KeyTypedEvent extends KeyEvent {
    private final char character;

    public KeyTypedEvent(Widget target, char character) {
        super(target, Keys.valueOf(String.valueOf(character).toUpperCase()));
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
