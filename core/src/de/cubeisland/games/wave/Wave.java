package de.cubeisland.games.wave;

import de.cubeisland.games.entity.Entity;

import java.util.List;

public class Wave {
    private final List<Entity> entities;
    private int number;

    public Wave(int number, List<Entity> entities) {
        this.number = number;
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public boolean isCompleted() {
        for (Entity entity : this.entities) {
            if (entity.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public int getNumber() {
        return number;
    }
}
