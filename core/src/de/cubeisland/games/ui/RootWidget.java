package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.screen.AbstractScreen;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget<T extends Base2DGame> extends Container implements EventSender {

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
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
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

    @Override
    public void trigger(Event event) {

    }

    private final class UiInputProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {

            return false; // TODO focus
        }

        @Override
        public boolean keyUp(int keycode) {
            return false; // TODO focus
        }

        @Override
        public boolean keyTyped(char character) {
            return false; // TODO focus
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false; // TODO intersection
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false; // TODO intersection
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false; // TODO intersection
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false; // TODO intersection
        }

        @Override
        public boolean scrolled(int amount) {
            return false; // TODO intersection
        }
    }
}
