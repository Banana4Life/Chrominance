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
import de.cubeisland.games.util.Profiler;

public abstract class AbstractScreen<T extends Base2DGame> implements Screen {

    private static final float MAX_DELTA = 1f / 30f;

    private final T game;
    private final RootWidget<T> rootWidget;
    private final OrthographicCamera uiCamera;
    private final DrawContext context;

    AbstractScreen(T game) {
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
    public void show() {
    }

    @Override
    public void hide() {
        this.dispose();
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
        Profiler.begin("AbstractScreen.render");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (delta > MAX_DELTA) {
            System.err.println("Long frame: " + delta);
            delta = MAX_DELTA;
        }

        try {
            Profiler.begin("AbstractScreen.render[game]");
            this.renderScreen(getGame(), delta);
            Profiler.end();

            Profiler.begin("AbstractScreen.render[UI]");
            this.rootWidget.render(this.context);
            Profiler.end();
        } catch (RuntimeException e) {
            e.printStackTrace(System.err);
            Gdx.app.exit();
        }
        Profiler.end();
    }

    public abstract void renderScreen(T game, float delta);

    @Override
    public void dispose() {
        this.rootWidget.dispose();
        this.context.getBatch().dispose();
        this.context.getShapeRenderer().dispose();
    }
}
