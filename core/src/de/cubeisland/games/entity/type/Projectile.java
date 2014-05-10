package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.entity.Move;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.EntityType;

public abstract class Projectile extends EntityType {
    protected Projectile() {
        add(Render.class);
        add(Move.class);
    }

    public abstract float launchSpeed();
}
