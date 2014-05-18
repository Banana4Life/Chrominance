package de.cubeisland.games.ui;

import java.util.ArrayList;
import java.util.List;

public class WidgetManager {
    private final List<Widget> rootLevelWidgets;

    public WidgetManager() {
        this.rootLevelWidgets = new ArrayList<>();
    }

    public WidgetManager addWidget(Widget widget) {
        this.rootLevelWidgets.add(widget);
        return this;
    }

    public WidgetManager removeWidget(Widget widget) {
        this.rootLevelWidgets.remove(widget);
        return this;
    }

    public void renderWidgets() {
        for (Widget widget : this.rootLevelWidgets) {
            widget.render();
        }
    }

    public void dispose() {

    }
}
