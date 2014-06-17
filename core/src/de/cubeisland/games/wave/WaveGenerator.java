package de.cubeisland.games.wave;

import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.level.MapStructure;

public interface WaveGenerator {
    Wave generate(EntityFactory entityFactory, int waveNumber, MapStructure map);
}
