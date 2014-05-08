package de.cubeisland.games.util;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by phill_000 on 08.05.2014.
 */
public class ImmutableVector2 extends Vector2 {
    @Override
    public Vector2 set(Vector2 v) {
        throw new UnsupportedOperationException("This vector is immutable!");
    }

    @Override
    public Vector2 set(float x, float y) {
        throw new UnsupportedOperationException("This vector is immutable!");
    }


}
