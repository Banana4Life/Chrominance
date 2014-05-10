package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;

public class Bullet extends Projectile {

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setColor(Color.YELLOW)
                .setRadius(4);
    }

    @Override
    public float launchSpeed() {
        return 100;
    }
}
