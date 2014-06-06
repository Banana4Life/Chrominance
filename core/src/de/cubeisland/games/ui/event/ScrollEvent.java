package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.Widget;

public class ScrollEvent extends Event {
    private final Widget target;
    private final int amount;

    public ScrollEvent(Widget target, int amount) {
        this.target = target;
        this.amount = amount;
    }

    public Widget getTarget() {
        return target;
    }

    public int getAmount() {
        return amount;
    }
}
