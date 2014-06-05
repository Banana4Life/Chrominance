package de.cubeisland.games.entity.type;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Circle;
import de.cubeisland.games.collision.CollisionSource;
import de.cubeisland.games.collision.CollisionVolume;
import de.cubeisland.games.component.entity.Collidable;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Bullet extends Projectile implements CollisionSource {

    private final Circle collisionVolume = new Circle(4);

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(collisionVolume.getRadius());

        System.out.println("Pew!");
    }

    @Override
    public float getLaunchSpeed() {
        return 100;
    }

    @Override
    public CollisionVolume getCollisionVolume() {
        return collisionVolume;
    }

    @Override
    public void onCollide(Entity e, Collidable collidable, Vector2 minimumTranslationVector) {
        System.out.println("Boom!");
        e.die();
    }
}
