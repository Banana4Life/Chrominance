package de.cubeisland.games.component;

public interface KeyboardInput {
    boolean onKeyDown(int keyCode);

    boolean onKeyUp(int keyCode);

    boolean onKeyTyped(char character);
}
