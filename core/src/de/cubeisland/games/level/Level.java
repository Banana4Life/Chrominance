package de.cubeisland.games.level;

import de.cubeisland.games.component.ComponentHolder;
import de.cubeisland.games.entity.Entity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by phill_000 on 28.04.2014.
 */
public class Level extends ComponentHolder<Level>
{
    private final List<Entity> entities;

    public Level() {
        entities = new CopyOnWriteArrayList<Entity>();
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(this.entities);
    }
}
