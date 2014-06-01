package de.cubeisland.games.ui.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.cubeisland.games.ui.DrawContext;
import de.cubeisland.games.ui.Widget;

public class Container extends Widget {

    private Texture backgroundTexture;
    private BackgroundMode backgroundMode = BackgroundMode.STRETCH;

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public Container setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        return this;
    }

    public BackgroundMode getBackgroundMode() {
        return backgroundMode;
    }

    public Container setBackgroundMode(BackgroundMode backgroundMode) {
        this.backgroundMode = backgroundMode;
        return this;
    }

    @Override
    protected void draw(DrawContext context) {
        super.draw(context);

        if (this.backgroundTexture == null) {
            return;
        }

        Batch b = context.getBatch();
        b.begin();

        if (getBackgroundMode() == BackgroundMode.STATIC) {
            b.draw(this.backgroundTexture, getAbsoluteX(), getAbsoluteY(), this.backgroundTexture.getWidth(), this.backgroundTexture.getHeight());
        } else if (getBackgroundMode() == BackgroundMode.STRETCH) {
            drawStretched(b, this.backgroundTexture);
        } else {
            drawTiled(b, this.backgroundTexture);
        }

        b.end();
    }

    protected void drawStretched(Batch b, Texture texture) {
        b.draw(texture, getAbsoluteX(), getAbsoluteY(), getWidth(), getHeight());
    }

    protected void drawTiled(Batch b, Texture texture) {
        drawStretched(b, texture); // TODO implement background tiling
    }
}
