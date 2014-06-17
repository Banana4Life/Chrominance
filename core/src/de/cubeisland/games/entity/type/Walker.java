package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Walker extends Enemy {

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        final Circle collisionVolume = new Circle(10);

        e.get(ColorContainer.class)
                .setColor(Color.BLUE);
        e.get(Render.class)
                .setRadius(collisionVolume.getRadius());
        e.get(Collidable.class)
                .setVolume(collisionVolume);
        e.get(PathFollower.class)
                .setSpeed(10);
    }
}
