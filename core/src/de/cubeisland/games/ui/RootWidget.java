package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.screen.AbstractScreen;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget<T extends Base2DGame> extends Container {

    private Vector2 position;
    private final AbstractScreen<T> screen;

    public RootWidget(AbstractScreen<T> screen) {
        this.screen = screen;
        setSizing(Sizing.STATIC);
        setAlignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        setForegroundColor(Color.BLACK);
    }

    public AbstractScreen<T> getScreen() {
        return screen;
    }

    @Override
    public Widget getParent() {
        return null;
    }

    @Override
    public RootWidget getRoot() {
        return this;
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
        return Gdx.graphics.getHeight();
    }

    @Override
    public final float getContentHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected final void draw(DrawContext context) {
    }
}
