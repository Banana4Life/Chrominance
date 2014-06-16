package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;

public interface CollisionSourceHandler {
    void onCollide(Collider collider, Collidable collidable, Vector2 minimumTranslationVector);
}
