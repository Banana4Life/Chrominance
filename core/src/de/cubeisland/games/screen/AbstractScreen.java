package de.cubeisland.games.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.RootWidget;

public abstract class AbstractScreen<T extends Base2DGame> implements Screen {
    private final T game;
    private final RootWidget<T> rootWidget;
    private final OrthographicCamera uiCamera;
    private final DrawContext context;

    public AbstractScreen(T game) {
        this.game = game;
        this.rootWidget = new RootWidget<>(this);
        this.uiCamera = new OrthographicCamera();
        this.uiCamera.setToOrtho(true);

        ShapeRenderer uiShapeRenderer = new ShapeRenderer();
        uiShapeRenderer.setProjectionMatrix(this.uiCamera.combined);
        SpriteBatch uiBatch = new SpriteBatch();
        uiBatch.setProjectionMatrix(this.uiCamera.combined);

        this.context = new DrawContext(game, this.uiCamera, uiShapeRenderer, uiBatch);
    }

    public T getGame() {
        return game;
    }

    public RootWidget<T> getRootWidget() {
        return rootWidget;
    }

    @Override
    public final void resize(int width, int height) {
        this.uiCamera.setToOrtho(true, width, height);
        onResize(width, height);
    }

    protected void onResize(int width, int height) {
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.renderScreen(getGame(), delta);

        this.rootWidget.render(this.context);
    }

    public abstract void renderScreen(T game, float delta);

    @Override
    public void dispose() {
        this.rootWidget.dispose();
    }
}
