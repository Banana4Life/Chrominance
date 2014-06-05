package de.cubeisland.games.screen;

import de.cubeisland.games.Base2DGame;

public abstract class AbstractGameScreen<T extends Base2DGame> extends AbstractScreen<T> {
    private boolean paused;

    public AbstractGameScreen(T game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

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
