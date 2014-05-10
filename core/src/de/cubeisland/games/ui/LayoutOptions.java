package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

import static de.cubeisland.games.util.VectorUtil.zero;


public class LayoutOptions {

    private final Container.Alignment alignment;
    private final BitmapFont font, titleFont;
    private final Vector2 padding;

    public LayoutOptions(Container.Alignment alignment, BitmapFont font, BitmapFont titleFont, Vector2 padding) {
        this.alignment = alignment;
        this.font = font;
        this.titleFont = titleFont;
        this.padding = padding;
    }

    public Container.Alignment getAlignment() {
        return this.alignment;
    }

    public BitmapFont getFont() {
        return font;
    }

    public BitmapFont getTitleFont() {
        return titleFont;
    }

    public Vector2 getPadding() {
        return padding;
    }

    public static class Builder {
        private Container.Alignment  alignment = Container.Alignment.CENTER;
        private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf"));
        private BitmapFont font = generator.generateFont(40);
        private BitmapFont titleFont = generator.generateFont(60);
        private Vector2    padding = zero();

        public Builder() {
            generator.dispose();
            font.setColor(0.7f, 0.7f, 0.7f, 1);
            titleFont.setColor(0.7f, 0.7f, 0.7f, 1);
        }
        public Builder alignment(Container.Alignment alignment) {
            this.alignment = alignment;
            return this;
        }
        public Builder font(BitmapFont font) {
            this.font = font;
            return this;
        }
        public Builder titleFont(BitmapFont font) {
            this.titleFont = font;
            return this;
        }
        public Builder padding(Vector2 padding) {
            this.padding = padding;
            return this;
        }
        public LayoutOptions build() {
            return new LayoutOptions(alignment, font, titleFont, padding);
        }
    }
}
