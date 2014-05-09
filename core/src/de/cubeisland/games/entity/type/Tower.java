package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.EntityType;

public class Tower extends EntityType {
    public Tower() {
        this.add(Render.class);
    }
}
