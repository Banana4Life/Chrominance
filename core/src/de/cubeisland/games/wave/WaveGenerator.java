package de.cubeisland.games.wave;

import de.cubeisland.games.entity.EntityFactory;

public interface WaveGenerator {
    Wave generate(EntityFactory entityFactory, int waveNumber, Difficulty difficulty);
}
