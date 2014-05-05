package de.cubeisland.games.wave;

import de.cubeisland.games.entity.Entity;

import java.util.List;

public class Wave {
    private final List<Entity> entities;

    public Wave(List<Entity> entities) {
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
}
