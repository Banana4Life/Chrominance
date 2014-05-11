package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.entity.Collidable;
import de.cubeisland.games.component.entity.Collider;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Level;

import java.util.HashSet;
import java.util.Set;

public class CollisionDetector {
    private final Level level;

    public CollisionDetector(Level level) {
        this.level = level;
    }

    public void collide() {

        if (this.level.getEntities().isEmpty()) {
            return;
        }

        final Set<Collider> colliders = new HashSet<>();
        final Set<Collidable> collidables = new HashSet<>();

        for (Entity e : this.level.getEntities()) {
            Collider collider = e.get(Collider.class);
            if (collider != null) {
                colliders.add(collider);
            }

            Collidable collidable = e.get(Collidable.class);
            if (collidable != null) {
                collidables.add(collidable);
            }
        }

        if (colliders.isEmpty() || collidables.isEmpty()) {
            return;
        }

        final Set<UnorderedPair> checkedPairs = new HashSet<>();
        for (Collider collider : colliders) {
            for (Collidable collidable : collidables) {
                if (collider.getOwner() == collidable.getOwner()) {
                    continue;
                }
                UnorderedPair pair = new UnorderedPair(collider, collidable);
                if (checkedPairs.contains(pair)) {
                    continue;
                }
                this.resolveCollision(collider, collidable);
                checkedPairs.add(pair);
            }
        }
    }

    private void resolveCollision(Collider collider, Collidable collidable) {
        if (!collider.getOwner().isAlive() || !collidable.getOwner().isAlive()) {
            return;
        }

        CollisionVolume colliderVolume = collider.getHandler().getCollisionVolume();
        CollisionVolume collidableVolume = collidable.getHandler().getCollisionVolume();

        if (colliderVolume instanceof Circle && collidableVolume instanceof Circle) {
            this.resolveCircleCicleCollision(collider, (Circle) colliderVolume, collidable, (Circle) collidableVolume);
        }
    }

    private void resolveCircleCicleCollision(Collider collider, Circle colliderCircle, Collidable collidable, Circle collidableCircle) {
        final Vector2 source = collider.getOwner().getLocation().cpy();
        final Vector2 target = collidable.getOwner().getLocation().cpy();
        final Vector2 distance = target.sub(source);

        float minDistance = colliderCircle.getRadius() + collidableCircle.getRadius();
//        System.out.print(collider.getOwner().getLocation());
//        System.out.print(collidable.getOwner().getLocation());
//        System.out.println(distance);
//        System.out.println("Distance²: " + distance.len2() + ", minDistance²: " + (minDistance * minDistance));
        if (distance.len2() < minDistance * minDistance) {
            final float distanceLength = distance.len();
            final Vector2 normal = distance.scl(1f/distanceLength);
            final Vector2 minimumTranslationVector = normal.scl(distanceLength - minDistance);

            collider.onCollide(collidable, minimumTranslationVector);
            collidable.onCollide(collider, minimumTranslationVector);
        }
    }

    private static final class UnorderedPair {
        private final Collider collider;
        private final Collidable collidable;

        private UnorderedPair(Collider collider, Collidable collidable) {
            this.collider = collider;
            this.collidable = collidable;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            UnorderedPair other = (UnorderedPair) obj;
            return this.collider.equals(other.collider) && this.collidable.equals(this.collidable);
        }

        @Override
        public int hashCode() {
            return this.collider.hashCode() + this.collidable.hashCode();
        }
    }
}
