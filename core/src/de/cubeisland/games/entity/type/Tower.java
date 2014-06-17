package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.collision.CollisionTargetHandler;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import sun.corba.EncapsInputStreamFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Tower extends EntityType {
    private final Color idColor;
    private Vector2 centerOffset = new Vector2(0, 0);
    private List<Vector2> muzzleOffset = new ArrayList<>();
    private Texture turretTexture;
    private Texture baseTexture;
    private float maxShots = 10;
    private float maxRotationPerTick = 300;
    private float targetRange = 100;
    private long cooldown = 1500;

    private Projectile projectile = new Projectile();

    public Tower(Color idColor) {
        this.idColor = idColor.cpy();
        add(TowerRender.class);
        add(ProjectileLauncher.class);
        add(ColorContainer.class);
        add(SoundPlayer.class);
        add(Rotator.class);
        add(Collidable.class);

        muzzleOffset.add(new Vector2(0, 0));
    }

    public Color getIdColor() {
        return this.idColor.cpy();
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        final float dimension = e.getLevel().getMap().getScale();

        e.setVelocity(new Vector2(1, 0).setAngle(0));

        e.get(TowerRender.class)
                .setTurretTexture(turretTexture)
                .setBaseTexture(baseTexture);
        e.get(ProjectileLauncher.class)
                .setCooldown(cooldown, MILLISECONDS)
                .setTargetRange(targetRange)
                .setProjectile(projectile)
                .setMuzzleOffset(muzzleOffset);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE)
                .setMaxAmount(maxShots)
                .refill();
        e.get(SoundPlayer.class)
                .setSound(e.getLevel().getScreen().getGame().resources.sounds.pew);
        e.get(Rotator.class)
                .setCenterOffset(centerOffset)
                .setMaxRotationPerTick(maxRotationPerTick);
        e.get(ClickBounds.class)
                .setBoundShape(new ClickBounds.RectangularBound(dimension, dimension))
                .setOffset(new Vector2(dimension / -2f, dimension / -2f));
        e.get(Collidable.class)
                .setVolume(new Circle(dimension / 2f))
                .setHandler(new CollisionTargetHandler() {
                    @Override
                    public void onCollide(Collidable collidable, Collider collider, Vector2 minimumTranslationVector) {
                        Entity entity = collider.getOwner();
                        Entity tower  = collidable.getOwner();
                        if (entity.getType() instanceof ColorDrop && entity.get(ColorContainer.class).getColor().equals(tower.get(ColorContainer.class).getColor())) {
                            ColorContainer cc = collidable.getOwner().get(ColorContainer.class);
                            ColorContainer repo = entity.get(Spawner.class).get().get(ColorContainer.class);

                            double diff = cc.getMaxAmount() - cc.getAmount();
                            diff = Math.min(diff, repo.getAmount());

                            System.out.println("Transferring: " + diff);

                            cc.addAmount(diff);
                            repo.subAmount(diff);
                        }
                    }
                });
    }

    public Tower setCenterOffset(Vector2 centerOffset) {
        this.centerOffset = centerOffset;
        return this;
    }

    public Tower setMuzzleOffset(List<Vector2> muzzleOffset) {
        this.muzzleOffset = muzzleOffset;
        return this;
    }

    public Tower setTurretTexture(Texture texture) {
        this.turretTexture = texture;
        return this;
    }

    public Tower setBaseTexture(Texture texture) {
        this.baseTexture = texture;
        return this;
    }

    public Tower setMaxShots(float maxShots) {
        this.maxShots = maxShots;
        return this;
    }

    public Tower setMaxRotationPerTick(float maxRotationPerTick) {
        this.maxRotationPerTick = maxRotationPerTick;
        return this;
    }

    public Tower setTargetRange(float targetRange) {
        this.targetRange = targetRange;
        return this;
    }

    public Tower setCooldown(long cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public Tower setProjectile(Projectile projectile) {
        this.projectile = projectile;
        return this;
    }
}
