package de.cubeisland.games.entity.type;

import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.entity.ColorDropRenderer;
import de.cubeisland.games.component.entity.MouseFollower;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class ColorDrop extends EntityType {
    public ColorDrop() {
        add(Collider.class);
        add(ColorRepoValue.class);
        add(MouseFollower.class);
        add(ColorDropRenderer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);


    }
}
