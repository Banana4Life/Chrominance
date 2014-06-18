package de.cubeisland.games.wave;

import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Shield;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityFactory;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.level.MapStructure;

import java.util.ArrayList;
import java.util.List;

public class PredefinedWaveGenerator implements WaveGenerator {
    private WaveStructure waveStructure;

    public PredefinedWaveGenerator(WaveStructure waveStructure) {
        this.waveStructure = waveStructure;
    }

    @Override
    public Wave generate(EntityFactory entityFactory, int waveNumber, MapStructure map) {
        List<Entity> entities = new ArrayList<>();
        for (int i = 0; i < waveStructure.getPathCount(waveNumber); i++) {
            for (int n = 0; n < waveStructure.getEntityCount(waveNumber, i); n++) {
                Entity e = entityFactory.createEntity(waveStructure.getEntityType(waveNumber, i, n));
                e.get(PathFollower.class).setPath(map.getPaths().get(i));
                entities.add(e);
            }
        }

        return new Wave(waveNumber, entities);
    }

    public WaveStructure getWaveStructure() {
        return waveStructure;
    }
}