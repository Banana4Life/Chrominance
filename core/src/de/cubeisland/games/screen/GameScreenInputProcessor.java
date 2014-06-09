package de.cubeisland.games.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.component.FingerInput;
import de.cubeisland.games.component.KeyboardInput;
import de.cubeisland.games.component.MouseInput;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Level;

class GameScreenInputProcessor implements InputProcessor {
    private final GameScreen gameScreen;

    GameScreenInputProcessor(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private Vector3 unproject(int x, int y) {
        Vector3 pos = new Vector3(x, y, 0);
        gameScreen.getGame().getCamera().unproject(pos);

        // round to exact pixels
        pos.x = (int)(pos.x + .5);
        pos.y = (int)(pos.y + .5);

        return pos;
    }

    private Level getLevel() {
        return this.gameScreen.getLevel();
    }

    @Override
    public boolean keyDown(int keyCode) {
        KeyboardInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(KeyboardInput.class);
            if (input != null && input.onKeyDown(keyCode)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(KeyboardInput.class);
            if (input != null && input.onKeyDown(keyCode)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean keyUp(int keyCode) {
        KeyboardInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(KeyboardInput.class);
            if (input != null && input.onKeyUp(keyCode)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(KeyboardInput.class);
            if (input != null && input.onKeyUp(keyCode)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean keyTyped(char character) {
        KeyboardInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(KeyboardInput.class);
            if (input != null && input.onKeyTyped(character)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(KeyboardInput.class);
            if (input != null && input.onKeyTyped(character)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = unproject(screenX, screenY);
        FingerInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(FingerInput.class);
            if (input != null && input.onTouchDown(pos.x, pos.y, pointer, button)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(FingerInput.class);
            if (input != null && input.onTouchDown(pos.x, pos.y, pointer, button)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = unproject(screenX, screenY);
        FingerInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(FingerInput.class);
            if (input != null && input.onTouchUp(pos.x, pos.y, pointer, button)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(FingerInput.class);
            if (input != null && input.onTouchUp(pos.x, pos.y, pointer, button)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 pos = unproject(screenX, screenY);
        FingerInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(FingerInput.class);
            if (input != null && input.onTouchDragged(pos.x, pos.y, pointer)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(FingerInput.class);
            if (input != null && input.onTouchDragged(pos.x, pos.y, pointer)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 pos = unproject(screenX, screenY);
        MouseInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(MouseInput.class);
            if (input != null && input.onMouseMoved(pos.x, pos.y)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(MouseInput.class);
            if (input != null && input.onMouseMoved(pos.x, pos.y)) {
                handled = true;
            }
        }
        return handled;
    }

    @Override
    public boolean scrolled(int amount) {
        MouseInput input;
        boolean handled = false;
        for (Entity entity : getLevel().getEntities()) {
            input = entity.findImpl(MouseInput.class);
            if (input != null && input.onScrolled(amount)) {
                handled = true;
                break;
            }
        }
        if (!handled) {
            input = getLevel().findImpl(MouseInput.class);
            if (input != null && input.onScrolled(amount)) {
                handled = true;
            }
        }
        return handled;
    }
}
