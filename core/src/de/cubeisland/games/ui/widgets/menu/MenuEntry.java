package de.cubeisland.games.ui.widgets.menu;

import de.cubeisland.games.ui.HorizontalAlignment;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;

public class MenuEntry extends Container {
    public MenuEntry(String text) {
        Widget label = new Label().setText(text);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);
        addChild(label);

        setPadding(15, 30);
    }
}
