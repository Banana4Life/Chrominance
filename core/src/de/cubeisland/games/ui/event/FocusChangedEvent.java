package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.Widget;

public class FocusChangedEvent implements Event {
    private final Widget oldWidget;
    private final Widget newWidget;

    public FocusChangedEvent(Widget oldWidget, Widget newWidget) {
        this.oldWidget = oldWidget;
        this.newWidget = newWidget;
    }

    public Widget getOldWidget() {
        return oldWidget;
    }

    public Widget getNewWidget() {
        return newWidget;
    }
}
