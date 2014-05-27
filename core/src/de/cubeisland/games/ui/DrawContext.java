package de.cubeisland.games.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.Base2DGame;

public class DrawContext {
    private final Base2DGame game;
    private final ShapeRenderer shapeRenderer;
    private final Batch batch;

    public DrawContext(Base2DGame game, ShapeRenderer shapeRenderer, Batch batch) {
        this.game = game;
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
    }

    public Base2DGame getGame() {
        return game;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public Batch getBatch() {
        return batch;
    }
}
