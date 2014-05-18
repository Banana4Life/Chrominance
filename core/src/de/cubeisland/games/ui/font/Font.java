package de.cubeisland.games.ui.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import de.cubeisland.games.ui.Invalidatable;
import de.cubeisland.games.ui.Widget;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Font implements Invalidatable {
    private final FreeTypeFontGenerator generator;
    private SizeDefinition size;
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
        this.size = new StaticSize(size);
        return this;
    }

    public Font setSize(Widget container, float percentage) {
        this.size = new PercentageSize(container, percentage);
        return this;
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

    @Override
    public void invalidate() {
        this.bitmapFont.dispose();
        this.bitmapFont = null;
    }
}
