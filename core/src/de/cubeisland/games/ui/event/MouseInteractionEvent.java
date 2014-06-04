package de.cubeisland.games.ui.event;

import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.Widget;

public class MouseInteractionEvent implements Event {
    private final Widget widget;

    public MouseInteractionEvent(Widget widget) {
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }
}
