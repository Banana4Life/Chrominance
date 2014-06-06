package de.cubeisland.games.entity.type;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Circle;
import de.cubeisland.games.collision.CollisionSource;
import de.cubeisland.games.collision.CollisionVolume;
import de.cubeisland.games.component.entity.Collidable;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.Move;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class Projectile extends EntityType implements CollisionSource {
    private float launchSpeed = 300;
    private Circle collisionVolume = new Circle(4);
    private MuzzleFlash muzzleFlash = new MuzzleFlash();

    protected Projectile() {
        add(Render.class);
        add(Move.class);
        add(ColorContainer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(collisionVolume.getRadius());
    }

    public Projectile setLaunchSpeed(float launchSpeed) {
        this.launchSpeed = launchSpeed;
        return this;
    }
    public float getLaunchSpeed() {
        return launchSpeed;
    }

    public Projectile setCollisionVolume(float radius) {
        collisionVolume = new Circle(radius);
        return this;
    }
    @Override
    public CollisionVolume getCollisionVolume() {
        return collisionVolume;
    }

    @Override
    public void onCollide(Entity e, Collidable collidable, Vector2 minimumTranslationVector) {
        e.die();
    }

    public MuzzleFlash getMuzzleFlash() {
        return muzzleFlash;
    }
}