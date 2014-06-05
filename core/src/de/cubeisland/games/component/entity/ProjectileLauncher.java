package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.After;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.type.Enemy;
import de.cubeisland.games.entity.type.Projectile;
import de.cubeisland.games.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@After(Rotator.class)
public class ProjectileLauncher extends Component<Entity> {

    private Projectile projectile;
    private float targetRange = 30;
    private float targetRangeSquared = this.targetRange * this.targetRange;
    private long cooldown = 0;
    private long lastFired = 0;
    private Entity target;
    private List<Vector2> muzzleOffset = new ArrayList<>();
    private int muzzleCount = 0;

    @Override
    public void update(float delta) {
        Rotator rotator = getOwner().get(Rotator.class);
        if (rotator != null && rotator.isAimed()) {
            shoot();
        }

        target = this.findNearestTarget(getOwner().getLocation().cpy());
    }

    protected void shoot() {
        if (cooldown > 0 && System.currentTimeMillis() - lastFired < cooldown) {
            return;
        }
        if (!getOwner().get(ColorContainer.class).hasShots()) {
            return;
        }

        Vector2 muzzle = getMuzzle();

        Entity p = getOwner().getLevel().spawn(this.projectile, muzzle);
        p.setVelocity(new Vector2(projectile.getLaunchSpeed(), 0))
         .setAngle(getOwner().get(Rotator.class).getRotation()).get(ColorContainer.class)
         .setColor(getOwner().get(ColorContainer.class).getColor());

        getOwner().getLevel().spawn(projectile.getMuzzleFlash(), muzzle);

        getOwner().trigger(this, new ProjectileLaunchEvent(p));
        getOwner().get(ColorContainer.class).shoot();

        this.lastFired = System.currentTimeMillis();
    }

    protected Vector2 getMuzzle() {
        if (muzzleCount >= muzzleOffset.size()) {
            muzzleCount = 0;
        }
        Rotator rotator = getOwner().get(Rotator.class);
        return rotator.getAbsolutePos(rotator.getCenterPos(), muzzleOffset.get(muzzleCount++));
    }

    protected Entity findNearestTarget(Vector2 loc) {
        float smallestDistance = Float.MAX_VALUE;
        Entity nearestEntity = null;
        for (Entity e : getOwner().getLevel().getEntities()) {
            if (e.getType() instanceof Enemy) {
                if (e.get(ColorContainer.class).getColor() == getOwner().get(ColorContainer.class).getColor()) {
                    float distanceSquared = loc.cpy().sub(e.getLocation()).len2();
                    float distanceToPathEnd = e.get(PathFollower.class).getDistanceToPathTarget();
                    if (distanceSquared < this.targetRangeSquared && distanceToPathEnd < smallestDistance) {
                        smallestDistance = distanceToPathEnd;
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

    public ProjectileLauncher setMuzzleOffset(List<Vector2> muzzleOffset) {
        this.muzzleOffset = muzzleOffset;
        return this;
    }

    public Entity getTarget() {
        return target;
    }

    public static class ProjectileLaunchEvent implements Event {
        private final Entity projectile;

        private ProjectileLaunchEvent(Entity projectile) {
            this.projectile = projectile;
        }

        public Entity getProjectile() {
            return this.projectile;
        }
    }
}
