package de.cubeisland.games.wave;

import de.cubeisland.games.entity.Entity;

import java.util.Iterator;
import java.util.List;

public class Wave {
    private final List<Entity> entities;
    private final Iterator<Entity> iterator;
    private int number;

    public Wave(int number, List<Entity> entities) {
        this.number = number;
        this.entities = entities;
        this.iterator = entities.iterator();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public boolean isCompleted() {
        for (Entity entity : entities) {
            if (entity.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public int getNumber() {
        return number;
    }

    public boolean hasMoreEntities() {
        return this.iterator.hasNext();
    }

    public Entity nextEntity() {
        return this.iterator.next();
    }
}
