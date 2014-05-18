package de.cubeisland.games.entity.type;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Circle;
import de.cubeisland.games.collision.CollisionSource;
import de.cubeisland.games.collision.CollisionVolume;
import de.cubeisland.games.component.entity.Collidable;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

import java.awt.*;

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
    public float launchSpeed() {
        return 1000;
    }

    @Override
    public CollisionVolume getCollisionVolume() {
        return collisionVolume;
    }

    @Override
    public void onCollide(Entity e, Collidable collidable, Vector2 minimumTranslationVector) {
        System.out.println("Boom!");
        if (e.get(ColorContainer.class).getColor().r > 0) {
            System.out.println("red: " + collidable.getOwner().get(ColorContainer.class).getColor().r);
        }
        if (e.get(ColorContainer.class).getColor().g > 0) {
            System.out.println("green: " + collidable.getOwner().get(ColorContainer.class).getColor().g);
        }
        if (e.get(ColorContainer.class).getColor().b > 0) {
            System.out.println("blue: " + collidable.getOwner().get(ColorContainer.class).getColor().b);
        }

        e.die();
    }
}
