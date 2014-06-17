package de.cubeisland.games.collision;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Collider extends Component<Entity> {

    private ShapeRenderer renderer;
    private CollisionVolume volume;
    private CollisionSourceHandler handler;

    @Override
    protected void onInit() {
        super.onInit();

        this.renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta) {
        if (!getOwner().getLevel().getScreen().getGame().isDebug()) {
            return;
        }
        if (volume instanceof Circle) {
            Vector2 pos = getOwner().getLocation();
            this.renderer.begin(ShapeRenderer.ShapeType.Line);
            this.renderer.setColor(Color.GREEN);
            this.renderer.circle(pos.x, pos.y, ((Circle) volume).getRadius());
            this.renderer.end();
        }
    }

    public CollisionVolume getVolume() {
        return volume;
    }

    public Collider setVolume(CollisionVolume volume) {
        this.volume = volume;
        return this;
    }

    public boolean hasVolume() {
        return this.volume != null;
    }

    public CollisionSourceHandler getHandler() {
        return handler;
    }

    public Collider setHandler(CollisionSourceHandler handler) {
        this.handler = handler;
        return this;
    }

    public boolean hasHandler() {
        return this.handler != null;
    }

    public void onCollide(Collidable collidable, Vector2 minimalTranslationVector) {
        if (hasHandler()) {
            this.handler.onCollide(this, collidable, minimalTranslationVector);
        }
    }
}
