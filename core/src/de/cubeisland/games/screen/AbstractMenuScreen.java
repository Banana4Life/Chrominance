package de.cubeisland.games.screen;

import com.badlogic.gdx.Screen;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.widgets.menu.Menu;
import de.cubeisland.games.ui.widgets.menu.MenuAction;

import java.util.Stack;

public abstract class AbstractMenuScreen<T extends Base2DGame> extends AbstractScreen<T> {
    public static final MenuAction BACK = new BackMenuAction();

    private final Stack<Menu<T>> menus;

    public AbstractMenuScreen(T game) {
        super(game);
        this.menus = new Stack<>();
    }

    public boolean hasMenus() {
        return !this.menus.empty();
    }

    public Menu getCurrentMenu() {
        return menus.peek();
    }

    public AbstractMenuScreen<T> pushMenu(Menu<T> menu) {
        if (hasMenus()) {
            getRootWidget().removeChild(getCurrentMenu());
        }
        this.menus.push(menu);
        getRootWidget().addChild(menu);
        return this;
    }

    public AbstractMenuScreen<T> popMenu() {
        if (hasMenus()) {
            Menu<T> menu = this.menus.pop();
            getRootWidget().removeChild(menu);
        }
        return this;
    }

    @Override
    public final void show() {
        super.show();
        onShow();
    }

    public void onShow() {
    }

    @Override
    public final void hide() {
        super.hide();
        onHide();
    }

    public void onHide() {
    }

    @Override
    public final void pause() {
    }

    @Override
    public final void resume() {
    }

    private static class BackMenuAction implements MenuAction {
        @Override
        public void go(Screen screen) {
            if (screen instanceof AbstractMenuScreen) {
                ((AbstractMenuScreen) screen).popMenu();
            }
        }
    }
}
