package de.cubeisland.games.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.Base2DGame;

class GameScreenInputProcessor<T extends Base2DGame> implements InputProcessor {
    private final AbstractGameScreen<T> gameScreen;

    GameScreenInputProcessor(AbstractGameScreen<T> gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = new Vector3(screenX, screenY, 0);
        System.out.println(pos);
        gameScreen.getGame().getCamera().unproject(pos);
        System.out.println(pos);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
