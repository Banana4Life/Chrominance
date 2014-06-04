package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.screen.AbstractScreen;
import de.cubeisland.games.ui.event.KeyDownEvent;
import de.cubeisland.games.ui.event.KeyTypedEvent;
import de.cubeisland.games.ui.event.KeyUpEvent;
import de.cubeisland.games.ui.event.ScrollEvent;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget<T extends Base2DGame> extends Container implements EventSender {

    private final AbstractScreen<T> screen;
    private final UiInputProcessor inputProcessor;

    private Widget focusedWidget;

    public RootWidget(AbstractScreen<T> screen) {
        this.screen = screen;
        inputProcessor = new UiInputProcessor();
        this.screen.getGame().getInput().addProcessor(inputProcessor);
        setSizing(Sizing.STATIC);
        setAlignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        setForegroundColor(Color.BLACK);
        this.focusedWidget = this;
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
    public void dispose() {
        super.dispose();
        getScreen().getGame().getInput().removeProcessor(this.inputProcessor);
    }

    public Widget getFocusedWidget() {
        return this.focusedWidget;
    }

    public RootWidget<T> focus(Widget widget) {
        if (!isFocused(widget)) {
            this.focusedWidget = widget;
        }
        return this;
    }

    public boolean isFocused(Widget widget) {
        return getFocusedWidget() == widget;
    }

    public RootWidget<T> unfocus(Widget w) {
        if (isFocused(w)) {
            focus(this);
        }
        return this;
    }

    @Override
    public boolean trigger(Event event) {
        return getFocusedWidget().trigger(this, event);
    }

    private final class UiInputProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            System.out.println("keydown!");
            Widget target = getFocusedWidget();
            return target.trigger(RootWidget.this, new KeyDownEvent(target, keycode));
        }

        @Override
        public boolean keyUp(int keycode) {
            System.out.println("keyup!");
            Widget target = getFocusedWidget();
            return target.trigger(RootWidget.this, new KeyUpEvent(target, keycode));
        }

        @Override
        public boolean keyTyped(char character) {
            System.out.println("keytyped!");
            Widget target = getFocusedWidget();
            return target.trigger(RootWidget.this, new KeyTypedEvent(target, character));
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            System.out.println("touch down at [" + screenX + ":" + screenY + "] !");
            return false; // TODO intersection
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            System.out.println("touch up at [" + screenX + ":" + screenY + "] !");
            return false; // TODO intersection
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            System.out.println("touch dragged to [" + screenX + ":" + screenY + "] !");
            return false; // TODO intersection
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            System.out.println("mouse moved to [" + screenX + ":" + screenY + "] !");
            return false; // TODO intersection
        }

        @Override
        public boolean scrolled(int amount) {
            System.out.println("scrolled for " + amount + " !");
            Widget target = getFocusedWidget();
            return target.trigger(RootWidget.this, new ScrollEvent(target, amount));
        }
    }
}
