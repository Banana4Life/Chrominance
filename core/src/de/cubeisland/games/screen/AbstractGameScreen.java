package de.cubeisland.games.screen;

import de.cubeisland.games.Base2DGame;

public abstract class AbstractGameScreen<T extends Base2DGame> extends AbstractScreen<T> {
    private boolean paused;

    public AbstractGameScreen(T game) {
        super(game);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        this.paused = true;
    }

    @Override
    public void resume() {
        this.paused = false;
    }
}
