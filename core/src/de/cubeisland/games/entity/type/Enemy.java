package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class Enemy extends EntityType {
    public Enemy() {
        add(PathFollower.class);
        add(Move.class);
        add(ColorContainer.class);
        add(Render.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(10);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE);
    }
}
