package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.FingerInput;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;

@Require(ClickBounds.class)
public class ClickKiller extends Component<Entity> implements FingerInput {

    private boolean touching = false;

    @Override
    public void update(float delta) {
    }

    @Override
    public boolean onTouchDown(float x, float y, int pointer, int button) {
        if (getOwner().get(ClickBounds.class).contains(x, y)) {
            this.touching = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchUp(float x, float y, int pointer, int button) {
        if (this.touching) {
            this.touching = false;
            getOwner().die();
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchDragged(float x, float y, int pointer) {
        return false;
    }
}
