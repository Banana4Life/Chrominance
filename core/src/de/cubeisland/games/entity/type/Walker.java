package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Circle;
import de.cubeisland.games.collision.CollisionVolume;
import de.cubeisland.games.component.entity.Collider;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Walker extends Enemy {

    private final Circle collisionVolume = new Circle(10);

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(ColorContainer.class)
                .setColor(Color.BLUE);
        e.get(Render.class)
                .setRadius(collisionVolume.getRadius());
        e.get(PathFollower.class)
                .setSpeed(10);
    }

    @Override
    public CollisionVolume getCollisionVolume() {
        return collisionVolume;
    }

    @Override
    public void onCollide(Entity e, Collider collider, Vector2 minimumTranslationVector) {
        e.die();
    }
}
