package de.cubeisland.games.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Malte on 29.04.2014.
 */
public class MenuOptions {

    public enum Alignment {
        LEFT, CENTER, RIGHT
    }

    private final static Alignment DEFAULT_alignment = Alignment.CENTER;
    private final static BitmapFont DEFAULT_font = new BitmapFont();
    private final static Vector2 DEFAULT_padding = Vector2.Zero;

    private final Alignment alignment;
    private final BitmapFont font;
    private final Vector2 padding;

    public MenuOptions() {
        this(DEFAULT_alignment, DEFAULT_font, DEFAULT_padding);
    }

    public MenuOptions(Alignment alignment) {
        this(alignment, DEFAULT_font, DEFAULT_padding);
    }

    public MenuOptions(Alignment alignment, BitmapFont font) {
        this(alignment, font, DEFAULT_padding);
    }

    public MenuOptions(Alignment alignment, Vector2 padding) {
        this(alignment, DEFAULT_font, padding);
    }

    public MenuOptions(BitmapFont font) {
        this(DEFAULT_alignment, font, DEFAULT_padding);
    }

    public MenuOptions(BitmapFont font, Vector2 padding) {
        this(DEFAULT_alignment, font, padding);
    }

    public MenuOptions(Vector2 padding) {
        this(DEFAULT_alignment, DEFAULT_font, padding);
    }

    public MenuOptions(Alignment alignment, BitmapFont font, Vector2 padding) {
        this.alignment = alignment;
        this.font = font;
        this.padding = padding;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Vector2 getPadding() {
        return padding;
    }

}
