package de.cubeisland.games.screen;

import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.widgets.menu.Menu;

public abstract class AbstractMenuScreen<T extends Base2DGame> extends AbstractScreen<T> {
    private final Menu menu;

    public AbstractMenuScreen(T game, Menu menu) {
        super(game);
        this.menu = menu;
        this.getRootWidget().addChild(menu);
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public final void pause() {
    }

    @Override
    public final void resume() {
    }
}
