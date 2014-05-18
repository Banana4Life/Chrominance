package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget extends Container {

    private final Vector2 position = new Vector2(0, 0);

    @Override
    public Widget getParent() {
        return null;
    }

    @Override
    public final Vector2 getPosition() {
        return this.position;
    }

    @Override
    public final float getWidth() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public final float getContentWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    public final float getHeight() {
        return Gdx.graphics.getWidth();
    }

    @Override
    public final float getContentHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}
