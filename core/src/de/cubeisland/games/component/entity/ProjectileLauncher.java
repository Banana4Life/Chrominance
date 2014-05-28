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
    private float rotation = 0;
    private float maxRotationPerTick = 300;
    private Vector2 centerOffset = new Vector2(0, -10);

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
            Vector2 v = this.calculateVelocity(loc, target.get(PathFollower.class).getIntersection(loc, this.projectile.launchSpeed()), this.projectile.launchSpeed());
            if (v != null) {
                this.rotate(v.angle(), delta);
                if (rotation == v.angle()) {
                    getOwner().getLevel().spawn(this.projectile, loc).setVelocity(v).get(ColorContainer.class).setColor(getOwner().get(ColorContainer.class).getColor());
                    getOwner().get(ColorContainer.class).shoot();
                    this.lastFired = System.currentTimeMillis();
                }
            }
        }
    }

    protected Vector2 calculateVelocity(Vector2 loc, Entity target, float speed) {
        return target.getLocation().cpy().sub(loc).nor().scl(speed);
    }
    protected Vector2 calculateVelocity(Vector2 loc, Vector2 target, float speed) {
        if (target != null && loc != null) {
            return target.cpy().sub(loc).nor().scl(speed);
        } else {
            return null;
        }
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

    public ProjectileLauncher setRotation(float rotation) {
        this.rotation = getAngleInLimits(rotation);
        return this;
    }
    public ProjectileLauncher setMaxRotationPerTick(float maxRotationPerTick) {
        this.maxRotationPerTick = maxRotationPerTick;
        return this;
    }
    private void rotate(float newAngle, float delta) {
        newAngle = getAngleInLimits(newAngle);
        float leftRotation = getAngleInLimits(rotation - newAngle);
        float rightRotation = getAngleInLimits(newAngle - rotation);

        if (Math.abs(rotation - newAngle) <= maxRotationPerTick * delta) {
            rotation = newAngle;
        } else if(leftRotation < rightRotation) {
            rotation = getAngleInLimits(rotation - (maxRotationPerTick * delta));
        } else {
            rotation = getAngleInLimits(rotation + (maxRotationPerTick * delta));
        }
    }

    /**
     * Calculates the given angle to the range of 0-360
     * @param angle
     */
    private float getAngleInLimits(float angle) {
        if (angle < 0) {
            angle = 360 - (Math.abs(angle) % 360);
        } else if(angle > 360) {
            angle %= 360;
        }
        return angle;
    }

    public float getRotation() {
        return rotation;
    }

    public ProjectileLauncher setCenterOffset(float offsetX, float offsetY) {
        centerOffset = new Vector2(offsetX, offsetY);
        return this;
    }
    public Vector2 getCenterOffset() {
        return centerOffset.cpy();
    }
}
