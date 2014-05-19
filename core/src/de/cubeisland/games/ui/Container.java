package de.cubeisland.games.ui;

import com.badlogic.gdx.math.Vector2;


public class Container extends Element {
    public Container(Alignment alignment, Vector2 padding) {
        this(ElementType.CONTAINER, alignment, padding);
    }

    public Container(ElementType type, Alignment alignment, Vector2 padding) {
        super(type, alignment, padding);
    }
}
