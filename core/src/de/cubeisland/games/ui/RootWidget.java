package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget extends Container {

    private Vector2 position;

    @Override
    public Widget getParent() {
        return null;
    }

    @Override
    public final Vector2 getPosition() {
        if (this.position == null) {
            this.position = new Vector2(0, Gdx.graphics.getHeight());
        }
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

    @Override
    protected void recalculate() {
        this.position = null;
    }
}
