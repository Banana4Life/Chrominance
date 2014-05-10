package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.ProjectileLauncher;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Tower extends EntityType {
    public Tower() {
        add(Render.class);
        add(ProjectileLauncher.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setColor(Color.ORANGE)
                .setRadius(20);
        e.get(ProjectileLauncher.class)
                .setCooldown(1, SECONDS)
                .setTargetRange(80)
                .setProjectile(EntityTypes.BULLET);
    }
}
