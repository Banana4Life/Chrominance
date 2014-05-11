package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.ColorContainer;
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
        add(ColorContainer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(Render.class)
                .setRadius(20);
        e.get(ProjectileLauncher.class)
                .setCooldown(1, SECONDS)
                .setTargetRange(120)
                .setProjectile(EntityTypes.BULLET);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE)
                .setAmount(10);
    }
}
