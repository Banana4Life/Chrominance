package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.util.BetterBatch;
import de.cubeisland.games.util.LoggingInputMultiplexer;
import de.cubeisland.games.util.Profiler;

public abstract class Base2DGame extends Game {

    private BetterBatch batch;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private InputMultiplexer input;
    private final boolean debug;

    protected Base2DGame() {
        this.debug = System.getProperty("debug") != null;
        Profiler.setEnabled(this.debug);
        this.input = new LoggingInputMultiplexer();
    }

    @Override
    public void create() {
        this.camera = new OrthographicCamera();
        this.batch = new BetterBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.camera.setToOrtho(false);
        this.batch.setProjectionMatrix(this.camera.combined);
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);

        Gdx.input.setInputProcessor(this.input);
    }

    public InputMultiplexer getInput() {
        return this.input;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.batch.dispose();

        this.input.clear();
        this.batch = null;
        this.camera = null;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.camera.setToOrtho(false, width, height);
    }

    public BetterBatch getBatch() {
        return this.batch;
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public ShapeRenderer getShapeRenderer() {
        return this.shapeRenderer;
    }

    public int getFPS() {
        return Gdx.graphics.getFramesPerSecond();
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void exit() {
        Gdx.app.exit();
    }
}
