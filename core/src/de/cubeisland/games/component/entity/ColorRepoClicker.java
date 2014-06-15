package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.FingerInput;
import de.cubeisland.games.entity.Entity;

public class ColorRepoClicker extends Component<Entity> implements FingerInput {
    @Override
    public void update(float delta) {

    }

    @Override
    public boolean onTouchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean onTouchUp(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean onTouchDragged(float x, float y, int pointer) {
        return false;
    }
}
