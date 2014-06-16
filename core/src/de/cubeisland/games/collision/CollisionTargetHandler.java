package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;

public interface CollisionTargetHandler {
    void onCollide(Collidable collidable, Collider collider, Vector2 minimumTranslationVector);
}
