package de.cubeisland.games.ui.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {
    private final FreeTypeFontGenerator generator;
    private final boolean flipped;
    private final SizeDefinition size;
    private BitmapFont bitmapFont;
    private Font flippedVersion;

    protected Font(FreeTypeFontGenerator generator, boolean flipped, SizeDefinition size) {
        this.generator = generator;
        this.flipped = flipped;
        this.size = size;
        this.bitmapFont = null;
    }

    public Font(FreeTypeFontGenerator generator, boolean flipped, int size) {
        this(generator, flipped, new StaticSize(size));
    }

    public Font(FreeTypeFontGenerator generator, boolean flipped, Widget container, float percentage) {
        this(generator, flipped, new PercentageSize(container, percentage));
    }

    public Font setSize(int size) {
        return new Font(this.generator, this.flipped, size);
    }

    public Font setSize(Widget container, float percentage) {
        return new Font(this.generator, this.flipped, container, percentage);
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    public Font flipped() {
        if (this.flippedVersion == null) {
            this.flippedVersion = new Font(this.generator, !this.flipped, this.size);
            this.flippedVersion.flippedVersion = this;
        }
        return this.flippedVersion;
    }

    public int getSize() {
        return this.size.getSize();
    }

    public BitmapFont getBitmapFont() {
        if (this.bitmapFont == null) {
            invalidate();
            FreeTypeFontParameter param = new FreeTypeFontParameter();
            param.size = this.getSize();
            param.flip = this.flipped;
            this.bitmapFont = this.generator.generateFont(param);
        }
        return this.bitmapFont;
    }

    public void invalidate() {
        if (this.bitmapFont != null) {
            this.bitmapFont.dispose();
            this.bitmapFont = null;
        }
    }

    public Font copy() {
        return new Font(this.generator, this.flipped, this.size);
    }
}
