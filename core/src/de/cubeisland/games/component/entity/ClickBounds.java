package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class ClickBounds extends Component<Entity> {

    public static final BoundShape NOOP = new NoOpBound();

    private BoundShape boundShape = NOOP;
    private Vector2 offset = new Vector2(0, 0);
    private ShapeRenderer renderer;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

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

    public Vector2 getOffset() {
        return offset.cpy();
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset.cpy();
    }

    @Override
    public void onAttach() {
        super.onAttach();
        this.renderer.setColor(new Color(1, 1, 0, .5f));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.renderer.dispose();
        this.renderer = null;
    }

    @Override
    public void update(float delta) {
        if (!getOwner().getLevel().getScreen().getGame().isDebug()) {
            return;
        }

        Vector2 pos = getOwner().getLocation().cpy().add(offset);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.boundShape.draw(this.renderer, pos.x, pos.y);
        this.renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public boolean contains(float x, float y) {
        Vector2 pos = getOwner().getLocation().cpy().add(offset);

        return this.boundShape.contains(pos.x, pos.y, x, y);
    }

    public static interface BoundShape {
        boolean contains(float ox, float oy, float tx, float ty);
        void draw(ShapeRenderer renderer, float x, float y);
    }

    public static class NoOpBound implements BoundShape {
        @Override
        public boolean contains(float ox, float oy, float tx, float ty) {
            return false;
        }

        @Override
        public void draw(ShapeRenderer renderer, float x, float y) {
        }
    }

    public static class CircularBound implements BoundShape {

        private final float radius;

        public CircularBound(float radius) {
            this.radius = radius * radius;
        }

        public float getRadius() {
            return radius;
        }

        @Override
        public boolean contains(float ox, float oy, float tx, float ty) {
            final float dx = tx - ox;
            final float dy = ty - oy;
            return dx * dx + dy * dy <= radius;
        }

        @Override
        public void draw(ShapeRenderer renderer, float x, float y) {
            renderer.circle(x, y, radius);
        }
    }

    public static class RectangularBound implements BoundShape {

        private final float width;
        private final float height;

        public RectangularBound(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public float getWidth() {
            return width;
        }

        public float getHeight() {
            return height;
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

        @Override
        public void draw(ShapeRenderer renderer, float x, float y) {
            renderer.rect(x, y, width, height);
        }
    }
}
