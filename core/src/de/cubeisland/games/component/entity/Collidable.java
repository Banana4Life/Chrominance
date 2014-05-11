package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.CollisionTarget;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Collidable extends Component<Entity> {

    private CollisionTarget handler;

    @Override
    protected void onInit() {
        if (!(getOwner().getType() instanceof CollisionTarget)) {
            throw new IllegalArgumentException("Owner must be a " + CollisionTarget.class.getSimpleName() + "!");
        }
        this.handler = (CollisionTarget) getOwner().getType();
    }

    @Override
    public void update(float delta) {

    }

    public CollisionTarget getHandler() {
        return handler;
    }

    public void onCollide(Collider collider, Vector2 minimalTranslationVector) {
        this.handler.onCollide(getOwner(), collider, minimalTranslationVector);
    }
}
