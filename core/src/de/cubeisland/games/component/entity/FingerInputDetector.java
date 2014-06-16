package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.FingerInput;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.event.Event;

@Require(ClickBounds.class)
public class FingerInputDetector extends Component<Entity> implements FingerInput {

    private boolean touchDown = false;
    private boolean touchingOwner = false;

    @Override
    public void update(float delta) {

    }

    public boolean isTouchDown() {
        return this.touchDown;
    }

    @Override
    public boolean onTouchDown(float x, float y, int pointer, int button) {
        this.touchDown = true;
        if (getOwner().get(ClickBounds.class).contains(x, y)) {
            this.touchingOwner = true;
            trigger(new EntityBeginTouchEvent(getOwner()));
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchUp(float x, float y, int pointer, int button) {
        this.touchDown = false;
        if (this.touchingOwner) {
            this.touchingOwner = false;
            trigger(new EntityTouchedEvent(getOwner()));
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchDragged(float x, float y, int pointer) {
        return false;
    }

    protected abstract static class EntityTouchEvent extends Event {
        private final Entity touchedEntity;

        public EntityTouchEvent(Entity touchedEntity) {
            this.touchedEntity = touchedEntity;
        }

        public Entity getTouchedEntity() {
            return touchedEntity;
        }
    }

    public static class EntityTouchedEvent extends EntityTouchEvent {
        public EntityTouchedEvent(Entity touchedEntity) {
            super(touchedEntity);
        }
    }

    public static class EntityBeginTouchEvent extends EntityTouchEvent {
        public EntityBeginTouchEvent(Entity touchedEntity) {
            super(touchedEntity);
        }
    }
}
