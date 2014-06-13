package de.cubeisland.games.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.Base2DGame;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.screen.AbstractScreen;
import de.cubeisland.games.ui.event.FocusChangedEvent;
import de.cubeisland.games.ui.widgets.Container;

public class RootWidget<T extends Base2DGame> extends Container implements EventSender {

    private final AbstractScreen<T> screen;
    private final UiInputProcessor inputProcessor;

    private Widget focusedWidget;

    public RootWidget(AbstractScreen<T> screen) {
        this.screen = screen;
        this.inputProcessor = new UiInputProcessor(this);
        this.screen.getGame().getInput().addProcessor(inputProcessor);
        setSizing(Sizing.STATIC);
        setAlignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        setForegroundColor(Color.BLACK);
        this.focusedWidget = this;
    }

    public AbstractScreen<T> getScreen() {
        return this.screen;
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
            Widget old = getFocusedWidget();
            this.focusedWidget = widget;
            FocusChangedEvent event = new FocusChangedEvent(old, widget);
            old.trigger(this, event);
            widget.trigger(this, event);
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
    public boolean trigger(EventSender sender, Event event) {
        Widget focused = getFocusedWidget();
        if (focused == this) {
            return super.trigger(sender, event);
        } else {
            return focused.trigger(sender, event);
        }
    }
}
