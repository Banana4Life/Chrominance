package de.cubeisland.games.ui.event;

public class TouchDownEvent extends ScreenInputEvent {
    private final int pointer;
    private final int button;

    public TouchDownEvent(int x, int y, int pointer, int button) {
        super(x, y);
        this.pointer = pointer;
        this.button = button;
    }

    public int getPointer() {
        return pointer;
    }

    public int getButton() {
        return button;
    }
}
