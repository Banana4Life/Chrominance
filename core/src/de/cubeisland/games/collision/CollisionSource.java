package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.entity.Collidable;
import de.cubeisland.games.entity.Entity;

public interface CollisionSource extends CollisionObject {
    void onCollide(Entity e, Collidable collidable, Vector2 minimumTranslationVector);
}
