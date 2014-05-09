package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.entity.Health;
import de.cubeisland.games.component.entity.Move;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.EntityType;

public abstract class Enemy extends EntityType {
    public Enemy() {
        add(PathFollower.class);
        add(Move.class);
        add(Health.class);
        add(Render.class);
    }
}
