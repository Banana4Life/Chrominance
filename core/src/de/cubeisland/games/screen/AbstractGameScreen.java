package de.cubeisland.games.screen;

import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.widgets.menu.Menu;

public abstract class AbstractGameScreen<T extends Base2DGame> extends AbstractScreen<T> {
    private boolean paused;
    private Menu pauseMenu;
    private GameScreenInputProcessor inputProcessor;

    public AbstractGameScreen(T game) {
        this(game, null);
    }

    public AbstractGameScreen(T game, Menu pauseMenu) {
        super(game);
        this.pauseMenu = pauseMenu;
        this.getRootWidget().addChild(pauseMenu);
        pauseMenu.setActive(false);
    }

    @Override
    public final void show() {
        this.inputProcessor = new GameScreenInputProcessor<>(this);
        getGame().getInput().addProcessor(this.inputProcessor);

        onShow();
    }

    public void onShow() {
    }

    @Override
    public final void hide() {
        if (this.inputProcessor != null) {
            getGame().getInput().removeProcessor(this.inputProcessor);
            this.inputProcessor = null;
        }

        onHide();
    }

    public void onHide() {
    }

    public boolean isPaused() {
        return this.paused;
    }

    @Override
    public final void pause() {
        this.paused = true;
        onPause();
    }

    protected void onPause() {
    }

    @Override
    public final void resume() {
        this.paused = false;
        onResume();
    }

    protected void onResume() {
    }
}
