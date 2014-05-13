package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.type.Enemy;
import de.cubeisland.games.entity.type.Projectile;

import java.util.concurrent.TimeUnit;

public class ProjectileLauncher extends Component<Entity> {

    private Projectile projectile;
    private float targetRange = 30;
    private float targetRangeSquared = this.targetRange * this.targetRange;
    private long cooldown = 0;
    private long lastFired = 0;

    @Override
    public void update(float delta) {
        if (cooldown > 0 && System.currentTimeMillis() - lastFired < cooldown) {
            return;
        }

        if (!getOwner().has(ColorContainer.class)) {
            return;
        }

        Vector2 loc = getOwner().getLocation().cpy();

        Entity target = this.findNearestTarget(loc);
        if (target != null) {
            Vector2 v = this.calculateVelocity(loc, target, this.projectile.launchSpeed());
            getOwner().getLevel().spawn(this.projectile, loc).setVelocity(v).get(ColorContainer.class).setColor(getOwner().get(ColorContainer.class).getColor());
            getOwner().get(ColorContainer.class).shoot();
            this.lastFired = System.currentTimeMillis();
        }
    }

    protected Vector2 calculateVelocity(Vector2 loc, Entity target, float speed) {
        return target.getLocation().cpy().sub(loc).nor().scl(speed);
    }

    protected Entity findNearestTarget(Vector2 loc) {
        float smallestDistance = Float.MAX_VALUE;
        Entity nearestEntity = null;
        for (Entity e : getOwner().getLevel().getEntities()) {
            if (e.getType() instanceof Enemy) {
                // TODO Check wheater the enemy contains the color and not exactly the color
                if ((e.get(ColorContainer.class).getColor().r > 0 && getOwner().get(ColorContainer.class).getColor().r > 0) ||
                   (e.get(ColorContainer.class).getColor().g > 0 && getOwner().get(ColorContainer.class).getColor().g > 0) ||
                   (e.get(ColorContainer.class).getColor().b > 0 && getOwner().get(ColorContainer.class).getColor().b > 0)) {
                    float distanceSquared = loc.cpy().sub(e.getLocation()).len2();
                    if (distanceSquared < this.targetRangeSquared && distanceSquared < smallestDistance) {
                        smallestDistance = distanceSquared;
                        nearestEntity = e;
                        break; // Added break to stop the loop after first enemy
                    }
                }
            }
        }

        return nearestEntity;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public ProjectileLauncher setProjectile(Projectile projectile) {
        this.projectile = projectile;
        return this;
    }

    public float getTargetRange() {
        return targetRange;
    }

    public ProjectileLauncher setTargetRange(float targetRange) {
        this.targetRange = targetRange;
        this.targetRangeSquared = targetRange * targetRange;
        return this;
    }

    public long getCooldown() {
        return cooldown;
    }

    public ProjectileLauncher setCooldown(long cooldown) {
        return this.setCooldown(cooldown, TimeUnit.MILLISECONDS);
    }

    public ProjectileLauncher setCooldown(long cooldown, TimeUnit unit) {
        this.cooldown = unit.toMillis(cooldown);
        return this;
    }
}
