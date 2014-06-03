package de.cubeisland.games.ui.event;

public class TouchDraggedEvent extends ScreenInputEvent {
    private final int pointer;

    public TouchDraggedEvent(int x, int y, int pointer) {
        super(x, y);
        this.pointer = pointer;
    }

    public int getPointer() {
        return pointer;
    }
}
