package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.CollisionSource;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Collider extends Component<Entity> {

    private CollisionSource handler;

    @Override
    protected void onInit() {
        if (!(getOwner().getType() instanceof CollisionSource)) {
            throw new IllegalArgumentException("Owner must be a " + CollisionSource.class.getSimpleName() + "!");
        }
        this.handler = (CollisionSource) getOwner().getType();
    }

    @Override
    public void update(float delta) {

    }

    public CollisionSource getHandler() {
        return handler;
    }

    public void onCollide(Collidable collidable, Vector2 minimalTranslationVector) {
        this.handler.onCollide(getOwner(), collidable, minimalTranslationVector);
    }
}
