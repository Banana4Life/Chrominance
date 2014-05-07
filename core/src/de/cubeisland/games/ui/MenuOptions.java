package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Malte on 29.04.2014.
 */
public class MenuOptions {

    public enum Alignment {
        LEFT, CENTER, RIGHT
    }

    private final Alignment alignment;
    private final BitmapFont font, titleFont;
    private final Vector2 padding;
    private final boolean paddingHit;

    public MenuOptions(Alignment alignment, BitmapFont font, BitmapFont titleFont, Vector2 padding, boolean paddingHit) {
        this.alignment = alignment;
        this.font = font;
        this.titleFont = titleFont;
        this.padding = padding;
        this.paddingHit = paddingHit;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public BitmapFont getFont() {
        return font;
    }

    public BitmapFont getTitleFont() { return titleFont; }

    public Vector2 getPadding() {
        return padding;
    }

    public boolean getPaddingHit() {
        return paddingHit;
    }

    public static class Builder {
        private Alignment  alignment = Alignment.CENTER;
        private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/neou/Neou-Bold.ttf"));
        private BitmapFont font = generator.generateFont(40);
        private BitmapFont titleFont = generator.generateFont(60);
        private Vector2    padding = Vector2.Zero;
        private boolean    paddingHit = true;

        public Builder() {
            generator.dispose();
            font.setColor(0.7f, 0.7f, 0.7f, 1);
            titleFont.setColor(0.7f, 0.7f, 0.7f, 1);
        }
        public Builder alignment(Alignment alignment) {
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
        public Builder paddingHit(boolean hit) {
            this.paddingHit = hit;
            return this;
        }
        public MenuOptions build() {
            return new MenuOptions(alignment, font, titleFont, padding, paddingHit);
        }
    }
}
