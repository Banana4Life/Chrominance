package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.Health;
import de.cubeisland.games.component.Move;
import de.cubeisland.games.component.PathFollower;
import de.cubeisland.games.component.Render;
import de.cubeisland.games.entity.EntityType;

public abstract class Enemy extends EntityType {
    public Enemy() {
        add(PathFollower.class);
        add(Move.class);
        add(Health.class);
        add(Render.class);
    }
}
