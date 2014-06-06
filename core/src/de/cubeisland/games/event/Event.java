package de.cubeisland.games.event;

public abstract class Event {
    private boolean handled;

    public final boolean wasHandled() {
        return this.handled;
    }

    public final void setHandled() {
        this.handled = true;
    }
}
