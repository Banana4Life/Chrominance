package de.cubeisland.games.ui.menu;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.ui.widgets.Container;

import static de.cubeisland.games.ui.Sizing.FILL_PARENT;

public abstract class Menu extends Container {

    protected Menu() {
        setHorizontalSizing(FILL_PARENT);
        setVerticalSizing(FILL_PARENT);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void recalculate() {
        super.recalculate();
    }
}
