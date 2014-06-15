package de.cubeisland.games.ui.widgets;

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
        this.font = font.copy();
        this.invalidate();
        return this;
    }

    public String getText() {
        return text;
    }

    public Label setText(String text) {
        this.text = text;
        invalidate();
        return this;
    }

    protected BitmapFont.TextBounds getBounds() {
        if (this.bounds == null) {
            this.bounds = getFont().getBitmapFont().getBounds(this.text);
        }
        return this.bounds;
    }

    @Override
    public void invalidate() {
        super.invalidate();

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
        BitmapFont font = getFont().getBitmapFont();
        font.setColor(getForegroundColor());
        font.draw(context.getBatch(), this.text, getAbsoluteX(), getAbsoluteY());
        b.end();
    }

    @Override
    public String toString() {
        return super.toString() + ":" + getText();
    }
}
