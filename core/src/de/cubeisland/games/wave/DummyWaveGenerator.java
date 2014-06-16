package de.cubeisland.games.wave;

import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.Shield;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityTypes;

import java.util.ArrayList;
import java.util.List;

public class DummyWaveGenerator implements WaveGenerator {
    private final Difficulty difficulty;

    public DummyWaveGenerator(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public Wave generate(EntityFactory entityFactory, int waveNumber, Difficulty difficulty) {
        List<Entity> entities = new ArrayList<>(waveNumber);
        for (int i = 0; i < waveNumber; ++i) {
            Entity e;
            if (i % 3 == 0) {
                e = entityFactory.createEntity(EntityTypes.WALKER);
            } else {
                e = entityFactory.createEntity(EntityTypes.RUNNER);
            }
            if (i % 4 == 0) {
                e.attach(Shield.class);
            }
            e.get(ColorContainer.class).setAmount(100 * (1 + waveNumber / 10));

            entities.add(e);
        }

        return new Wave(waveNumber, entities);
    }

    @Override
    public boolean hasFinished() {
        return false;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}