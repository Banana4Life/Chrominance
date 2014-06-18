package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.collision.Collidable;
import de.cubeisland.games.collision.Collider;
import de.cubeisland.games.collision.CollisionTargetHandler;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.component.entity.sound.DeathSoundPlayer;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.resource.ChrominanceResources;

public abstract class Enemy extends EntityType {
    private double life = 10;
    private Color color = null;
    private boolean hasShield = false;

    public Enemy() {
        add(PathFollower.class);
        add(Move.class);
        add(ColorContainer.class);
        add(Render.class);
        add(Collidable.class);
        add(DeathSoundPlayer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(DeathSoundPlayer.class)
                .setSound(e.getLevel().getScreen().getGame().resources.sounds.kill)
                .setVolume(.05f);
        e.get(Render.class)
                .setRadius(10);
        e.get(ColorContainer.class)
                .setColor(color)
                .setAmount(life);
        e.get(Collidable.class)
                .setHandler(new CollisionTargetHandler() {
                    @Override
                    public void onCollide(Collidable collidable, Collider collider, Vector2 minimumTranslationVector) {
                        if (Projectile.class.isAssignableFrom(collider.getOwner().getType().getClass())) {
                            if (!collidable.getOwner().has(Shield.class) && collidable.getOwner().get(ColorContainer.class).getColor().equals(collider.getOwner().get(ColorContainer.class).getColor())) {
                                collidable.getOwner().get(ColorContainer.class).subAmount(collider.getOwner().get(ColorContainer.class).getAmount());
                            } else if (collidable.getOwner().get(Shield.class).getColor().equals(collider.getOwner().get(ColorContainer.class).getColor())) {
                                collidable.getOwner().get(Shield.class).subAmount(collider.getOwner().get(ColorContainer.class).getAmount());
                            }
                        }
                    }
                });

        if (hasShield) {
            e.attach(Shield.class);
            e.get(Shield.class).setColor(color);
        }
    }

    public void setLife(double life) {
        this.life = life;
    }

    public Enemy setColor(Color color) {
        this.color = color;
        return this;
    }

    public Enemy setShield(boolean hasShield) {
        this.hasShield = hasShield;
        return this;
    }
}
