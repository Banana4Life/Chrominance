package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.Health;
import de.cubeisland.games.component.PathFollower;
import de.cubeisland.games.entity.EntityType;

public class Enemy extends EntityType {
    public Enemy() {
        add(PathFollower.class);
        add(Health.class);
    }
}
