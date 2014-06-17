package de.cubeisland.games.wave;

import de.cubeisland.games.entity.EntityType;

import java.util.List;

public class WaveStructure {
    private List<List<List<EntityType>>> waveData;

    public WaveStructure(List<List<List<EntityType>>> waveData) {
        this.waveData = waveData;
    }

    public boolean hasWaveAfter(int waveNumber) {
        return waveNumber < getWaveCount();
    }

    public int getWaveCount() {
        return waveData.size();
    }

    public int getPathCount(int waveNumber) {
        return waveData.get(waveNumber).size();
    }
    public int getEntityCount(int waveNumber, int pathNumber) {
        return waveData.get(waveNumber).get(pathNumber).size();
    }

    public EntityType getEntityType(int waveNumber, int pathNumber, int entityNumber) {
        return waveData.get(waveNumber).get(pathNumber).get(entityNumber);
    }
}
