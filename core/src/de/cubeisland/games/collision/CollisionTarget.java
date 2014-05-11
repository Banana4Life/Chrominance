package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.entity.Collider;
import de.cubeisland.games.entity.Entity;

public interface CollisionTarget extends CollisionObject {
    void onCollide(Entity e, Collider collider, Vector2 minimumTranslationVector);
}
