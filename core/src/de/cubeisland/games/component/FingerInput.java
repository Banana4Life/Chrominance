package de.cubeisland.games.component;

public interface FingerInput {
    public boolean onTouchDown(float x, float y, int pointer, int button);

    public boolean onTouchUp(float x, float y, int pointer, int button);

    public boolean onTouchDragged(float x, float y, int pointer);
}
