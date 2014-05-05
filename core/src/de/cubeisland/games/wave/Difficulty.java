package de.cubeisland.games.wave;

public enum Difficulty {
    DUMMY(0),
    EASY(.5f),
    NORMAL(1),
    HARD(2),
    IMPOSSIBLE(9001);

    private final float healthMultiplier;

    Difficulty(float healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public float getHealthMultiplier() {
        return healthMultiplier;
    }
}
