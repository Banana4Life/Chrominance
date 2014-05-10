package de.cubeisland.games.util;

import com.badlogic.gdx.math.Vector2;

public abstract class VectorUtil {

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public static Vector2 one() {
        return new Vector2(1, 1);
    }

    public static double distance(Vector2 from, Vector2 to) {
        return Math.sqrt(distanceSquared(from, to));
    }

    public static double distanceSquared(Vector2 from, Vector2 to) {
        final float diffX = to.x - from.x;
        final float diffY = to.y - from.y;
        return diffX * diffX + diffY * diffY;
    }
}
