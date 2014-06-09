package de.cubeisland.games.component.level;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.KeyboardInput;
import de.cubeisland.games.level.Level;

public class PauseMenuOpener extends Component<Level> implements KeyboardInput {

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean onKeyDown(int keyCode) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode) {
        if (getOwner().getScreen().isPaused()) {
            return false;
        }

        getOwner().getScreen().setPaused(true);

        return true;
    }

    @Override
    public boolean onKeyTyped(char character) {
        return false;
    }
}
