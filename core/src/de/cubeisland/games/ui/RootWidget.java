package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget extends Container {

    private final Vector2 position = new Vector2(0, 0);

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public float getWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getContentWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    public float getHeight() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public float getContentHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}
