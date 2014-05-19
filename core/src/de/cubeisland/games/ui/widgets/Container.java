package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class Container extends Widget {

    private final ShapeRenderer renderer = new ShapeRenderer();

    private Color backgroundColor = new Color(0, 0, 0, 0);

    public Color getBackgroundColor() {
        return backgroundColor.cpy();
    }

    public Container setBackgroundColor(Color color) {
        this.backgroundColor = color.cpy();
        return this;
    }

    @Override
    protected void draw() {
        super.draw();
        Vector2 pos = this.getAbsolutePosition();

        if (this.backgroundColor.a > 0) {
            this.renderer.begin(Filled);
            this.renderer.setColor(this.backgroundColor);
            this.renderer.rect(pos.x, pos.y, getWidth(), -getHeight());
            this.renderer.end();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
