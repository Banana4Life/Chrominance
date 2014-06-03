package de.cubeisland.games.ui.event;

public class TouchUpEvent extends ScreenInputEvent {
    private final int pointer;
    private final int button;

    public TouchUpEvent(int x, int y, int pointer, int button) {
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
