package de.cubeisland.games.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import de.cubeisland.games.ui.RootWidget;

public abstract class AbstractScreen<T extends Game> implements Screen {
    private final T game;
    private final RootWidget rootWidget;

    public AbstractScreen(T game) {
        this.game = game;
        this.rootWidget = new RootWidget();
    }

    public T getGame() {
        return game;
    }

    public RootWidget getRootWidget() {
        return rootWidget;
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderScreen(getGame(), delta);

        this.rootWidget.render();
    }

    public abstract void renderScreen(T game, float delta);

    @Override
    public void dispose() {
        this.rootWidget.dispose();
    }
}
