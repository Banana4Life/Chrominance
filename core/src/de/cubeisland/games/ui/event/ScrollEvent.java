package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;

public class ScrollEvent implements Event {
    private final int amount;

    public ScrollEvent(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
