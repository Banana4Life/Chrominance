package de.cubeisland.games.screen;

import com.badlogic.gdx.Game;
import de.cubeisland.games.BaseGame;

public abstract class AbstractGameScreen<T extends BaseGame> extends AbstractScreen<T> {
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
