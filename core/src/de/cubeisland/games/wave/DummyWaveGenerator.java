package de.cubeisland.games.wave;

import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityTypes;

import java.util.ArrayList;
import java.util.List;

public class DummyWaveGenerator implements WaveGenerator {
    @Override
    public Wave generate(EntityFactory entityFactory, int waveNumber, Difficulty difficulty) {
        List<Entity> entities = new ArrayList<>(waveNumber);
        for (int i = 0; i < waveNumber; ++i) {
            Entity e = entityFactory.createEntity(EntityTypes.RUNNER);
            e.get(ColorContainer.class).setAmount(100 * (1 + waveNumber / 10));

            entities.add(e);
        }

        return new Wave(waveNumber, entities);
    }
}