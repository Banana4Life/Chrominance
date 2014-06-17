package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Node;
import de.cubeisland.games.level.Path;

import java.util.List;

public class Rotator extends Component<Entity> {

    private float maxRotationPerTick = 300;
    private Vector2 centerOffset = new Vector2(0, 0);
    private boolean isAimed = false;

    @Override
    public void update(float delta) {
        ProjectileLauncher launcher = getOwner().get(ProjectileLauncher.class);
        if (launcher != null && launcher.getTarget() != null && !getOwner().get(ColorContainer.class).isEmpty()) {
            try {
                isAimed = rotate(getIntersection(getOwner().get(ProjectileLauncher.class).getTarget()).cpy().sub(getOwner().getLocation()).angle(), delta);
            } catch (NoIntersectionException e) {
                isAimed = false;
            }
        } else {
            isAimed = false;
        }
    }

    public boolean isAimed() {
        return isAimed;
    }

    public Vector2 getAbsolutePos(Vector2 pos, Vector2 offset) {
        return getAbsolutePos(pos, offset, getOwner().getVelocity().angle());
    }
    public static Vector2 getAbsolutePos(Vector2 pos, Vector2 offset, float degrees) {
        pos = pos.cpy();
        final float sinRot = (float)Math.sin(Math.toRadians(degrees));
        final float cosRot = (float)Math.cos(Math.toRadians(degrees));
        final float x = pos.x - offset.x * cosRot + offset.y * sinRot;
        final float y = pos.y - offset.x * sinRot - offset.y * cosRot;
        return new Vector2(x, y);
    }
    public Vector2 getPos() {
        final float scale = getOwner().getLevel().getMap().getScale();
        return getOwner().getLocation().cpy().sub(scale / 2, scale / 2);
    }
    public Vector2 getCenterPos() {
        return getAbsolutePos(getOwner().getLocation(), centerOffset).cpy();
    }

    public Rotator setMaxRotationPerTick(float maxRotationPerTick) {
        this.maxRotationPerTick = maxRotationPerTick;
        return this;
    }

    private boolean rotate(float newAngle, float delta) {
        newAngle = getAngleInLimits(newAngle);
        float rotation = getOwner().getVelocity().angle();
        float leftRotation = getAngleInLimits(rotation - newAngle);
        float rightRotation = getAngleInLimits(newAngle - rotation);

        if (Math.abs(rotation - newAngle) <= maxRotationPerTick * delta) {
            getOwner().getVelocity().setAngle(newAngle);
        } else if(leftRotation < rightRotation) {
            getOwner().getVelocity().setAngle(rotation - (maxRotationPerTick * delta));
        } else {
            getOwner().getVelocity().setAngle(rotation + (maxRotationPerTick * delta));
        }

        return getOwner().getVelocity().angle() == newAngle;
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

    public Rotator setCenterOffset(float offsetX, float offsetY) {
        centerOffset = new Vector2(offsetX, offsetY);
        return this;
    }
    public Rotator setCenterOffset(Vector2 centerOffset) {
        this.centerOffset = centerOffset.cpy();
        return this;
    }
    public Vector2 getCenterOffset() {
        return centerOffset.cpy();
    }

    public Vector2 getIntersection(Entity target) {
        if (target.getVelocity().len() < getOwner().get(ProjectileLauncher.class).getProjectile().getLaunchSpeed()) {
            return getIntersection(target, 0, 0);
        } else {
            throw new IllegalStateException("BulletSpeed has to be bigger than the enemy velocity");
        }
    }
    private Vector2 getIntersection(Entity target, int iteration, float i) {
        Vector2 ownPosition = getOwner().getLocation();
        float bulletSpeed = getOwner().get(ProjectileLauncher.class).getProjectile().getLaunchSpeed();

        Vector2 targetVelocity;
        Vector2 targetPosition;

        PathFollower pathFollower = target.get(PathFollower.class);
        Path path = pathFollower.getPath();
        int nodeNumber = pathFollower.getNodeNumber();

        if (iteration == 0) {
            targetPosition = target.getLocation().cpy();
            targetVelocity = target.getVelocity().cpy();
        } else {
            List<Node> nodes = path.getNodes();
            if (nodes.size() > nodeNumber + iteration) {
                targetPosition = nodes.get(nodeNumber + iteration - 1).getLocation().cpy();
                targetVelocity = nodes.get(nodeNumber + iteration).getLocation().cpy().sub(nodes.get(nodeNumber + iteration - 1).getLocation()).nor().scl(pathFollower.getSpeed());
            } else {
                throw new NoIntersectionException();
            }
        }

        float partOne = 2f * ((targetVelocity.x * targetVelocity.x) + (targetVelocity.y * targetVelocity.y) - (bulletSpeed * bulletSpeed));
        float partTwo = 2f * ((targetPosition.x * targetVelocity.x) + (targetPosition.y * targetVelocity.y) - (ownPosition.x * targetVelocity.x) - (ownPosition.y * targetVelocity.y) - (i * bulletSpeed * bulletSpeed));
        float partThree = (targetPosition.x * targetPosition.x) - (2f * targetPosition.x * ownPosition.x) + (targetPosition.y * targetPosition.y) - (2f * targetPosition.y * ownPosition.y) + (ownPosition.x * ownPosition.x) + (ownPosition.y * ownPosition.y) - (i * i * bulletSpeed * bulletSpeed);
        float x = (1f / partOne) * (-(float) Math.sqrt((partTwo * partTwo) - (2f * partOne * partThree)) - partTwo);

        Vector2 intersectionPos = targetVelocity.scl(Math.abs(x)).add(targetPosition);
        if (intersectionPos.cpy().sub(targetPosition).len() / path.getNodes().get(nodeNumber + iteration).getLocation().cpy().sub(targetPosition).len() > 1) {
            return getIntersection(target, iteration + 1, x + i);
        } else {
            return intersectionPos;
        }
    }

    private static class NoIntersectionException extends RuntimeException {
        public NoIntersectionException() {
            super("No intersection found for the target.");
        }
    }
}
