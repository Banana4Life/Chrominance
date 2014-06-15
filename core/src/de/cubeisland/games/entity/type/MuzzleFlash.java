package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.entity.LifeTimer;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

public class MuzzleFlash extends EntityType {
    private long lifeTime = 10;
    private Texture texture = null;

    public MuzzleFlash() {
        add(Render.class);
        add(LifeTimer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(LifeTimer.class)
                .setLifetime(lifeTime);
        e.get(Render.class)
                .setTexture(texture)
                .setRadius(6);
    }

    public MuzzleFlash setLifeTime(long timeInMillis) {
        lifeTime = timeInMillis;
        return this;
    }

    public MuzzleFlash setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }
}
