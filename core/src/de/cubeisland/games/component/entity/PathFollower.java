package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Before;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.event.Event;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Node;
import de.cubeisland.games.level.Path;

import static de.cubeisland.games.component.TickPhase.MOVEMENT;
import static de.cubeisland.games.util.VectorUtil.zero;

@Before(Move.class)
@Phase(MOVEMENT)
public class PathFollower extends Component<Entity> {
    private Path path;
    private Node currentTarget;
    private int nodeNumber = 1;
    private float tolerance = 3;
    private float scaledToleranceSquared;
    private float speed = 20;

    public PathFollower() {
        // set current value to calculate the scaled squared value
        this.setTolerance(this.tolerance);
    }

    public Path getPath() {
        return path;
    }

    public PathFollower setPath(Path path) {
        this.path = path;
        return this;
    }

    @Override
    public void update(float delta) {
        if (this.path != null) {
            if (this.currentTarget == null) {
                this.changeTarget();
            }
            Vector2 distance = this.currentTarget.getLocation().cpy().sub(getOwner().getLocation());
            if (withinTolerance(distance, delta)) {
                getOwner().getVelocity().set(distance);
                if (this.currentTarget == this.path.getTarget()) {
                    this.path = null;
                    this.currentTarget = null;
                    getOwner().setVelocity(zero());
                    getOwner().getLevel().subSaturation(0.1f);
                    emit(new PathCompleteEvent(this.path));
                } else {
                    this.changeTarget();
                }
            }
        }
    }

    private boolean withinTolerance(Vector2 distance, float delta) {
        return distance.len2() <= this.scaledToleranceSquared * delta;
    }

    private void changeTarget() {
        Node current = this.currentTarget;
        if (current == null) {
            current = this.path.getSpawn();
            this.nodeNumber = 0;
        }
        Node next = this.path.getNodes().get(++this.nodeNumber);

        Vector2 v = next.getLocation().cpy().sub(current.getLocation());
        v.nor().scl(this.speed);
        getOwner().setVelocity(v);

        this.currentTarget = next;
    }

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
        this.scaledToleranceSquared = getSpeed() / tolerance;
        this.scaledToleranceSquared *= this.scaledToleranceSquared;
    }

    public float getTolerance() {
        return tolerance;
    }

    public float getSpeed() {
        return speed;
    }

    public PathFollower setSpeed(float speed) {
        this.speed = speed;
        this.setTolerance(this.getTolerance());
        return this;
    }

    public Vector2 getIntersection(Vector2 towerPosition, float bulletSpeed) {
        Vector2 ownPosition = getOwner().getLocation().cpy();
        Vector2 ownVelocity = getOwner().getVelocity().cpy();
        float partOne = 2f * ((ownVelocity.x * ownVelocity.x) + (ownVelocity.y * ownVelocity.y) - (bulletSpeed * bulletSpeed));
        float partTwo = 2f * ((ownPosition.x * ownVelocity.x) + (ownPosition.y * ownVelocity.y) - (towerPosition.x * ownVelocity.x) - (towerPosition.y * ownVelocity.y));
        float partThree = (ownPosition.x * ownPosition.x) - (2f * ownPosition.x * towerPosition.x) + (ownPosition.y * ownPosition.y) - (2f * ownPosition.y * towerPosition.y) + (towerPosition.x * towerPosition.x) + (towerPosition.y * towerPosition.y);
        float i = (1f / partOne) * (-(float) Math.sqrt((partTwo * partTwo) - (2f * partOne * partThree)) - partTwo);
        return ownVelocity.scl(i).add(ownPosition);
    }

    public static class PathCompleteEvent implements Event {
        private final Path path;

        public PathCompleteEvent(Path path) {
            this.path = path;
        }

        public Path getPath() {
            return path;
        }
    }
}
