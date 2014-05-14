package de.cubeisland.games.collision;

public class Circle implements CollisionVolume {
    private final float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
}