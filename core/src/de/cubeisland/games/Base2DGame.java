package de.cubeisland.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Base2DGame extends Game {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        this.camera = new OrthographicCamera();
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        this.camera.setToOrtho(false);
        this.batch.setProjectionMatrix(this.camera.combined);
        this.shapeRenderer.setProjectionMatrix(this.camera.combined);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.batch.dispose();

        this.batch = null;
        this.camera = null;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.setToOrtho(false, width, height);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
