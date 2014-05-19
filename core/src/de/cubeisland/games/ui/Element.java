package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.Chrominance;

import static de.cubeisland.games.util.VectorUtil.zero;

public class Element {
    public static enum ElementType {
        CONTAINER, BUTTON, MENU, MENUITEM
    }

    public static enum Alignment {
        LEFT, CENTER, RIGHT
    }

    protected final ElementType type;
    protected Vector2 position;

    private final Alignment alignment;
    private final Vector2 padding;

    public Element(ElementType type, Alignment alignment, Vector2 padding) {
        this.type = type;
        this.alignment = alignment;
        this.padding = padding;
    }

    public Element(ElementType type, Alignment alignment) {
        this(type, alignment, zero());
    }

    public Element(ElementType type, Vector2 padding) {
        this(type, Alignment.CENTER, padding);
    }

    public Element(ElementType type) {
        this(type, Alignment.CENTER, zero());
    }

    public ElementType getType() {
        return this.type;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public Vector2 getPadding() {
        return padding;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void render(Chrominance game, float delta) {

    }
}
