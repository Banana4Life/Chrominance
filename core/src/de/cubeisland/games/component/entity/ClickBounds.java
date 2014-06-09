package de.cubeisland.games.component.entity;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class ClickBounds extends Component<Entity> {

    public static final BoundShape NOOP = new NoOpBound();

    private BoundShape boundShape = NOOP;

    public BoundShape getBoundShape() {
        return boundShape;
    }

    public ClickBounds setBoundShape(BoundShape boundShape) {
        if (boundShape == null) {
            throw new IllegalArgumentException("bound shape may not be null!");
        }
        this.boundShape = boundShape;
        return this;
    }

    @Override
    public void update(float delta) {
    }

    public boolean contains(float x, float y) {
        Vector2 pos = getOwner().getLocation();

        return this.boundShape.contains(pos.x, pos.y, x, y);
    }

    public static interface BoundShape {
        boolean contains(float ox, float oy, float tx, float ty);
    }

    public static class NoOpBound implements BoundShape {
        @Override
        public boolean contains(float ox, float oy, float tx, float ty) {
            return false;
        }
    }

    public static class CirclarBound implements BoundShape {

        private final float radius;

        public CirclarBound(float radius) {
            this.radius = radius * radius;
        }

        @Override
        public boolean contains(float ox, float oy, float tx, float ty) {
            final float dx = tx - ox;
            final float dy = ty - oy;
            return dx * dx + dy * dy <= radius;
        }
    }

    public static class RectangularBound implements BoundShape {

        private final float width;
        private final float height;

        public RectangularBound(float width, float height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public boolean contains(float ox, float oy, float tx, float ty) {
            if (tx < ox || tx > ox + width) {
                return false;
            }
            if (ty < oy || ty > oy + height) {
                return false;
            }
            return true;
        }
    }
}
