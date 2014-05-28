package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Sizing;
import de.cubeisland.games.ui.Widget;

public class Image extends Widget {
    private final Texture image;
    private float scale;

    public Image(Texture image) {
        if (image == null) {
            throw new IllegalArgumentException("image may not be null!");
        }
        this.image = image;
        this.scale = 1;
        super.setSizing(Sizing.STATIC);
    }

    public Texture getImage() {
        return image;
    }

    public float getScale() {
        return scale;
    }

    public Image setScale(float scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public float getWidth() {
        return getImage().getWidth() * scale;
    }

    @Override
    public float getHeight() {
        return getImage().getHeight() * scale;
    }

    @Override
    public Widget setSizing(Sizing sizing) {
        throw new UnsupportedOperationException("Sizing of images is static!");
    }

    @Override
    protected void draw(DrawContext context) {
        super.draw(context);

        Batch b = context.getBatch();

        final float width = getWidth();
        final float height = getHeight();

        b.begin();
        b.draw(getImage(), getAbsoluteX(), getAbsoluteY() - height, width, height);
        b.end();
    }
}
