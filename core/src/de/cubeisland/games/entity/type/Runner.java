package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Runner extends Enemy {
    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setColor(Color.DARK_GRAY)
                .setRadius(10);
        e.get(PathFollower.class)
                .setSpeed(80);
    }
}
