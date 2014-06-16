package de.cubeisland.games.wave;

public enum Difficulty {
    DUMMY(0, 10),
    EASY(.5f, 5),
    NORMAL(1, 25),
    HARD(2, 50),
    IMPOSSIBLE(9001, 500);

    private final float healthMultiplier;
    private final long waveCount;

    Difficulty(float healthMultiplier, long waveCount) {
        this.healthMultiplier = healthMultiplier;
        this.waveCount = waveCount;
    }

    public float getHealthMultiplier() {
        return healthMultiplier;
    }

    public long getWaveCount() { return waveCount; }
}
