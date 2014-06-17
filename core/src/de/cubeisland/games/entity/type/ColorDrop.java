package de.cubeisland.games.entity.type;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.collision.CollisionSourceHandler;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.ColorDropRenderer;
import de.cubeisland.games.component.entity.MouseFollower;
import de.cubeisland.games.component.entity.Spawner;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class ColorDrop extends EntityType {
    public ColorDrop() {
        add(Collider.class);
        add(MouseFollower.class);
        add(ColorDropRenderer.class);
        add(Spawner.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Collider.class)
                .setVolume(new Circle(e.get(ColorDropRenderer.class).getRadius()))
                .setHandler(new CollisionSourceHandler() {
                    @Override
                    public void onCollide(Collider collider, Collidable collidable, Vector2 minimumTranslationVector) {
                        Entity entity = collidable.getOwner();
                        Entity drop = collider.getOwner();
                        if (entity.getType() instanceof Tower && entity.get(ColorContainer.class).getColor().equals(drop.get(ColorContainer.class).getColor())) {
                            collider.getOwner().die();
                        }
                    }
                });
    }
}
