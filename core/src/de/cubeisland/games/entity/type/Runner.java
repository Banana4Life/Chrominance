package de.cubeisland.games.entity.type;

import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Runner extends Enemy {

    private final Circle collisionVolume = new Circle(10);

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(collisionVolume.getRadius());
        e.get(PathFollower.class)
                .setSpeed(80);
        e.get(Collidable.class)
                .setVolume(collisionVolume);
    }
}
