package de.cubeisland.games.ui.widgets.menu;

import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;
import de.cubeisland.games.ui.widgets.Container;
import de.cubeisland.games.ui.widgets.Label;

public class MenuEntry extends Container {
    public MenuEntry(String text, Font font) {
        Widget label = new Label().setText(text).setFont(font);

        setPadding(15, 30);
        addChild(label);
    }
}
