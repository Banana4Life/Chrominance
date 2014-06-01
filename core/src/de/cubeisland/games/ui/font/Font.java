package de.cubeisland.games.ui.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font {
    private final FreeTypeFontGenerator generator;
    private final SizeDefinition size;
    private BitmapFont bitmapFont;

    protected Font(FreeTypeFontGenerator generator, SizeDefinition size) {
        this.generator = generator;
        this.size = size;
        this.bitmapFont = null;
    }

    public Font(FreeTypeFontGenerator generator, int size) {
        this(generator, new StaticSize(size));
    }

    public Font(FreeTypeFontGenerator generator, Widget container, float percentage) {
        this(generator, new PercentageSize(container, percentage));
    }

    public Font setSize(int size) {
        return new Font(this.generator, size);
    }

    public Font setSize(Widget container, float percentage) {
        return new Font(this.generator, container, percentage);
    }

    public int getSize() {
        return this.size.getSize();
    }

    public BitmapFont getBitmapFont() {
        if (this.bitmapFont == null) {
            FreeTypeFontParameter param = new FreeTypeFontParameter();
            param.size = this.getSize();
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
        return new Font(this.generator, this.size);
    }
}
