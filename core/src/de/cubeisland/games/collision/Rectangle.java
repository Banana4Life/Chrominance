package de.cubeisland.games.collision;

import com.badlogic.gdx.math.Vector2;

public class Rectangle implements CollisionVolume {
    private final float upperX;
    private final float upperY;
    private final float lowerX;
    private final float lowerY;

    public Rectangle(float upperX, float upperY, float lowerX, float lowerY) {
        this.upperX = upperX;
        this.upperY = upperY;
        this.lowerX = lowerX;
        this.lowerY = lowerY;
    }

    public Rectangle(Vector2 upper, Vector2 lower) {
        this(upper.x, upper.y, lower.x, lower.y);
    }

    public Rectangle(com.badlogic.gdx.math.Rectangle rectangle) {
        this(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public float getUpperX() {
        return upperX;
    }

    public float getUpperY() {
        return upperY;
    }

    public float getLowerX() {
        return lowerX;
    }

    public float getLowerY() {
        return lowerY;
    }
}
