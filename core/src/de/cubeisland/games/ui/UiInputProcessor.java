package de.cubeisland.games.ui;

import com.badlogic.gdx.InputProcessor;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.event.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class UiInputProcessor implements InputProcessor {

    private final RootWidget root;
    private Set<Widget> hoveredWidgets = null;

    UiInputProcessor(RootWidget root) {
        this.root = root;
    }

    @SuppressWarnings("unchecked")
    private Widget invokeTouchEvents(Event event, int x, int y) {
        final TouchedWidgetIterator it = new TouchedWidgetIterator(this.root, x, y);
        Widget w = null;
        while (it.hasNext()) {
            w = it.next();
            w.trigger(event);
        }

        return w;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keydown!");
        final Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new KeyDownEvent(target, keycode));
    }

    @Override
    public boolean keyUp(int keycode) {
        final Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new KeyUpEvent(target, keycode));
    }

    @Override
    public boolean keyTyped(char character) {
        final Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new KeyTypedEvent(target, character));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        TouchDownEvent event = new TouchDownEvent(screenX, screenY, pointer, button);
        Widget w = invokeTouchEvents(event, screenX, screenY);
        if (w == null) {
            return false;
        }
        this.root.focus(w);
        return event.wasHandled();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        TouchUpEvent event = new TouchUpEvent(screenX, screenY, pointer, button);
        invokeTouchEvents(event, screenX, screenY);
        return event.wasHandled();
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        TouchDraggedEvent event = new TouchDraggedEvent(screenX, screenY, pointer);
        this.root.trigger(event);
        return event.wasHandled();
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Iterator<Widget> it = new TouchedWidgetIterator(this.root, screenX, screenY);
        HashSet<Widget> newState = new HashSet<>();
        while (it.hasNext()) {
            newState.add(it.next());
        }

        if (this.hoveredWidgets == null) {
            this.hoveredWidgets = newState;
        } else {
            for (Widget w : this.hoveredWidgets) {
                if (!newState.contains(w)) {
                    w.trigger(this.root, new MouseLeaveEvent(w));
                }
            }

            for (Widget w : newState) {
                if (!this.hoveredWidgets.contains(w)) {
                    w.trigger(this.root, new MouseEnterEvent(w));
                }
            }

            this.hoveredWidgets = newState;
        }

        return this.root.trigger(new MouseMovedEvent(screenX, screenY));
    }

    @Override
    public boolean scrolled(int amount) {
        Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new ScrollEvent(target, amount));
    }
}
