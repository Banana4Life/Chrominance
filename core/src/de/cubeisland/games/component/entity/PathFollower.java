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
    private float toleranceSquared = tolerance * tolerance;
    private float speed = 20;

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
            if (distance.len2() <= this.toleranceSquared) {
                getOwner().getLocation().add(distance);
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
        this.toleranceSquared = tolerance * tolerance;
    }

    public float getTolerance() {
        return tolerance;
    }

    public float getSpeed() {
        return speed;
    }

    public PathFollower setSpeed(float speed) {
        this.speed = speed;
        return this;
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
