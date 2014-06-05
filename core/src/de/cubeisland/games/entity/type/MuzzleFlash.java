package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.LifeTimer;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class MuzzleFlash extends EntityType {
    private long lifeTime = 10;

    protected MuzzleFlash() {
        add(Render.class);
        add(LifeTimer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(LifeTimer.class)
                .setLifetime(lifeTime);
        e.get(Render.class)
                .setColor(Color.YELLOW)
                .setRadius(6);
    }

    public MuzzleFlash setLifeTime(long timeInMillis) {
        lifeTime = timeInMillis;
        return this;
    }
}