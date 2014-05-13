package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.collision.CollisionTarget;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public abstract class Enemy extends EntityType implements CollisionTarget {
    private double life = 100;

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

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    /**
     * Deals damage and kills the enemy if life below zero
     * @param damage The damage dealt to the enemy
     * @return died Return true if life is below zero or false if above zero
     */
    public boolean dealDamage(double damage) {
        setLife(getLife() - damage);
        return (getLife() < 0);
    }
}
