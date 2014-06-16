package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.collision.CollisionTargetHandler;
import de.cubeisland.games.collision.volume.Circle;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public abstract class Enemy extends EntityType {
    private double life = 10;

    public Enemy() {
        add(PathFollower.class);
        add(Move.class);
        add(ColorContainer.class);
        add(Render.class);
        add(Collidable.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(10);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE)
                .setAmount(life);
        e.get(Collidable.class)
                .setHandler(new CollisionTargetHandler() {
                    @Override
                    public void onCollide(Collidable collidable, Collider collider, Vector2 minimumTranslationVector) {
                        if (Projectile.class.isAssignableFrom(collider.getOwner().getType().getClass())) {
                            if (!collidable.getOwner().has(Shield.class)) {
                                collidable.getOwner().get(ColorContainer.class).subAmount(collider.getOwner().get(ColorContainer.class).getAmount());
                            } else {
                                collidable.getOwner().get(Shield.class).subAmount(collider.getOwner().get(ColorContainer.class).getAmount());
                            }
                        }
                    }
                });
    }

    public void setLife(double life) {
        this.life = life;
    }
}
