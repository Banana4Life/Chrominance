package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.collision.CollisionSourceHandler;
import de.cubeisland.games.collision.CollisionVolume;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.Move;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class Projectile extends EntityType {
    private float launchSpeed = 300;
    private Circle collisionVolume = new Circle(4);
    private MuzzleFlash muzzleFlash = new MuzzleFlash();
    private Texture texture = null;
    private int damage = 1;

    public Projectile() {
        add(Render.class);
        add(Move.class);
        add(ColorContainer.class);
        add(Collider.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(collisionVolume.getRadius())
                .setTexture(texture);
        e.get(ColorContainer.class)
                .setAmount(damage);
        e.get(Collider.class)
                .setVolume(this.collisionVolume)
                .setHandler(new CollisionSourceHandler() {
                    @Override
                    public void onCollide(Collider collider, Collidable collidable, Vector2 minimumTranslationVector) {
                        collider.getOwner().die();
                    }
                });
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

    public MuzzleFlash getMuzzleFlash() {
        return muzzleFlash;
    }

    public Projectile setMuzzleFlash(MuzzleFlash muzzleFlash) {
        this.muzzleFlash = muzzleFlash;
        return this;
    }

    public Projectile setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public Projectile setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }
}