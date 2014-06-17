package de.cubeisland.games.wave;

import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.Shield;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityTypes;

import java.util.ArrayList;
import java.util.List;

public class DefaultWaveGenerator implements WaveGenerator {
    private final Difficulty difficulty;

    public DefaultWaveGenerator(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public Wave generate(EntityFactory entityFactory, int waveNumber) {
        List<Entity> entities = new ArrayList<>(waveNumber);
        for (int i = 0; i < waveNumber * 2; ++i) {
            Entity e;
            if (waveNumber % 3 == 0) {
                e = entityFactory.createEntity(EntityTypes.WALKER);
            } else if (waveNumber % 3 == 1) {
                e = entityFactory.createEntity(EntityTypes.RUNNER);
            } else {
                if (i % 3 == 0) {
                    e = entityFactory.createEntity(EntityTypes.WALKER);
                } else {
                    e = entityFactory.createEntity(EntityTypes.RUNNER);
                }
            }
            // First shield at 4th wave
            if (e != null && e.get(Collidable.class) != null && (waveNumber > 3 && i % 7 == 0)) {
                e.attach(Shield.class);
            }
            e.get(ColorContainer.class).setAmount(100 * (1 + (waveNumber / 10)) * (1 + getDifficulty().getHealthMultiplier()));

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