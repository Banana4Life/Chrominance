package de.cubeisland.games.screen;

import com.badlogic.gdx.Game;
import de.cubeisland.games.BaseGame;
import de.cubeisland.games.ui.menu.Menu;

public abstract class AbstractMenuScreen<T extends BaseGame> extends AbstractScreen<T> {
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
