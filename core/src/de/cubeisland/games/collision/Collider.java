package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Collider extends Component<Entity> {

    private CollisionVolume volume;
    private CollisionSourceHandler handler;

    @Override
    public void update(float delta) {

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
