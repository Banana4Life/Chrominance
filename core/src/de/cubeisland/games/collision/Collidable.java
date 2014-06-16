package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Collidable extends Component<Entity> {

    private CollisionTargetHandler handler;
    private CollisionVolume volume;

    @Override
    public void update(float delta) {

    }

    public CollisionVolume getVolume() {
        return volume;
    }

    public Collidable setVolume(CollisionVolume volume) {
        this.volume = volume;
        return this;
    }

    public boolean hasVolume() {
        return this.volume != null;
    }

    public CollisionTargetHandler getHandler() {
        return handler;
    }

    public Collidable setHandler(CollisionTargetHandler handler) {
        this.handler = handler;
        return this;
    }

    public boolean hasHandler() {
        return this.handler != null;
    }

    public void onCollide(Collider collider, Vector2 minimalTranslationVector) {
        if (hasVolume()) {
            this.handler.onCollide(this, collider, minimalTranslationVector);
        }
    }
}
