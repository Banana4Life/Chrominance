package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.RootWidget;
import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

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

        this.renderer.begin(Filled);
        this.renderer.setColor(Color.BLACK);
        this.renderer.circle(pos.x, pos.y, 5);
        this.renderer.setColor(Color.MAGENTA);
        this.renderer.circle(pos.x + getWidth(), pos.y - getHeight(), 5);
        this.renderer.end();

        this.renderer.begin(Line);
        this.renderer.setColor(Color.ORANGE);
        this.renderer.rect(pos.x, pos.y, getWidth(), -getHeight());
        this.renderer.end();

        if (this.backgroundColor.a > 0f) {
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
