package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Widget;
import de.cubeisland.games.ui.font.Font;

public class Label extends Widget {
    private String text;
    private Font font;
    private BitmapFont.TextBounds bounds;

    public Font getFont() {
        return font;
    }

    public Label setFont(Font font) {
        this.font = font;
        this.invalidate();
        return this;
    }

    public String getText() {
        return text;
    }

    public Label setText(String text) {
        this.text = text;
        this.invalidate();
        return this;
    }

    protected BitmapFont.TextBounds getBounds() {
        if (this.bounds == null) {
            this.bounds = getFont().getBitmapFont().getBounds(this.text);
        }
        return this.bounds;
    }

    @Override
    protected void recalculate() {
        super.recalculate();
        this.font.invalidate();
        this.bounds = null;
    }

    @Override
    public float getContentWidth() {
        return getBounds().width;
    }

    @Override
    public float getContentHeight() {
        return getBounds().height;
    }

    @Override
    protected void draw(DrawContext context) {
        super.draw(context);

        Batch b = context.getBatch();

        b.begin();
        BitmapFont font = this.font.getBitmapFont();
        font.setColor(getForegroundColor());
        font.draw(context.getBatch(), this.text, getX(), getY());
        b.end();
    }
}
