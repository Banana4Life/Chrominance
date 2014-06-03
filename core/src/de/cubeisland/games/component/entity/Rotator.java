package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Node;

import java.util.List;

public class Rotator extends Component<Entity> {

    private float rotation = 0;
    private float maxRotationPerTick = 300;
    private Vector2 centerOffset = new Vector2(0, 0);
    private boolean isAimed = false;

    @Override
    public void update(float delta) {
        isAimed = rotate(getIntersection(getOwner().get(ProjectileLauncher.class).getTarget()).cpy().angle(), delta);
    }

    public boolean isAimed() {
        return isAimed;
    }

    public Vector2 getAbsolutePos(Vector2 pos, Vector2 offset) {
        pos = pos.cpy();
        final float sinRot = (float)Math.sin(Math.toRadians(rotation));
        final float cosRot = (float)Math.cos(Math.toRadians(rotation));
        final float x = pos.x + offset.x * cosRot + offset.y * sinRot;
        final float y = pos.y - offset.x * sinRot - offset.y * cosRot;
        return new Vector2(x, y);
    }
    public Vector2 getPos() {
        final float scale = getOwner().getLevel().getMap().getScale();
        return getOwner().getLocation().cpy().sub(scale / 2, scale / 2);
    }
    public Vector2 getCenterPos() {
        final float scale = getOwner().getLevel().getMap().getScale();
        return getAbsolutePos(getPos(), centerOffset).cpy();
    }

    public float getRotation() {
        return rotation;
    }
    public Rotator setRotation(float rotation) {
        this.rotation = getAngleInLimits(rotation);
        return this;
    }
    public Rotator setMaxRotationPerTick(float maxRotationPerTick) {
        this.maxRotationPerTick = maxRotationPerTick;
        return this;
    }

    private boolean rotate(float newAngle, float delta) {
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

        if (rotation == newAngle) {
            return true;
        }
        return false;
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
        Vector2 ownVelocity;
        Vector2 ownPosition;
        if (iteration == 0) {
            ownPosition = getOwner().getLocation().cpy();
            ownVelocity = getOwner().getVelocity().cpy();
        } else {
            List<Node> nodes = path.getNodes();
            if (nodes.size() > nodeNumber + iteration) {
                ownPosition = nodes.get(nodeNumber + iteration - 1).getLocation().cpy();
                ownVelocity = nodes.get(nodeNumber + iteration).getLocation().cpy().sub(nodes.get(nodeNumber + iteration - 1).getLocation()).nor().scl(this.speed);
            } else {
                return null;
            }
        }

        float partOne = 2f * ((ownVelocity.x * ownVelocity.x) + (ownVelocity.y * ownVelocity.y) - (bulletSpeed * bulletSpeed));
        float partTwo = 2f * ((ownPosition.x * ownVelocity.x) + (ownPosition.y * ownVelocity.y) - (towerPosition.x * ownVelocity.x) - (towerPosition.y * ownVelocity.y) - (i * bulletSpeed * bulletSpeed));
        float partThree = (ownPosition.x * ownPosition.x) - (2f * ownPosition.x * towerPosition.x) + (ownPosition.y * ownPosition.y) - (2f * ownPosition.y * towerPosition.y) + (towerPosition.x * towerPosition.x) + (towerPosition.y * towerPosition.y) - (i * i * bulletSpeed * bulletSpeed);
        float x = (1f / partOne) * (-(float) Math.sqrt((partTwo * partTwo) - (2f * partOne * partThree)) - partTwo);

        Vector2 intersectionPos = ownVelocity.scl(x).add(ownPosition);
        if (intersectionPos.cpy().sub(ownPosition).len() / path.getNodes().get(nodeNumber + iteration).getLocation().cpy().sub(ownPosition).len() > 1) {
            return getIntersection(towerPosition, bulletSpeed, 1, x + i);
        } else {
            return intersectionPos;
        }
    }
}
