package de.cubeisland.games.ui.layout;

import de.cubeisland.games.ui.Widget;

import java.util.List;

public class ListLayout implements Layout {
    @Override
    public void positionWidgets(List<Widget> widgets) {

        float y = 0;

        for (Widget widget : widgets) {
            if (widget.getPositioning() != null) {
                continue;
            }
            widget.setY(y);
            y += widget.getMarginTop() + widget.getHeight() + widget.getMarginBottom();
        }
    }
}
