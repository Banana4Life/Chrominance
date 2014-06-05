package de.cubeisland.games.ui;

import com.badlogic.gdx.InputProcessor;
import de.cubeisland.games.event.Event;
import de.cubeisland.games.ui.event.*;

final class UiInputProcessor implements InputProcessor {

    private final RootWidget root;

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
        System.out.println("keyup!");
        final Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new KeyUpEvent(target, keycode));
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keytyped!");
        final Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new KeyTypedEvent(target, character));
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touch down at [" + screenX + ":" + screenY + "] !");

        Widget w = invokeTouchEvents(new TouchDownEvent(screenX, screenY, pointer, button), screenX, screenY);
        if (w == null) {
            return false;
        }
        this.root.focus(w);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touch up at [" + screenX + ":" + screenY + "] !");

        return invokeTouchEvents(new TouchUpEvent(screenX, screenY, pointer, button), screenX, screenY) != null;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touch dragged to [" + screenX + ":" + screenY + "] !");
        return this.root.trigger(new TouchDraggedEvent(screenX, screenY, pointer));
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return this.root.trigger(new MouseMovedEvent(screenX, screenY));
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("scrolled for " + amount + " !");
        Widget target = this.root.getFocusedWidget();
        return target.trigger(this.root, new ScrollEvent(target, amount));
    }
}
