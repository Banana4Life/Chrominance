package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.DrawContext;
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
    protected void draw(DrawContext context) {
        super.draw(context);
        Vector2 pos = this.getAbsolutePosition();

        final ShapeRenderer r = context.getShapeRenderer();

        r.begin(Filled);
        r.setColor(Color.BLACK);
        r.circle(pos.x, pos.y, 5);
        r.setColor(Color.MAGENTA);
        r.circle(pos.x + getWidth(), pos.y - getHeight(), 5);
        r.end();

        r.begin(Line);
        r.setColor(Color.ORANGE);
        r.rect(pos.x, pos.y, getWidth(), -getHeight());
        r.end();

        if (this.backgroundColor.a > 0f) {
            r.begin(Filled);
            r.setColor(this.backgroundColor);
            r.rect(pos.x, pos.y, getWidth(), -getHeight());
            r.end();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
