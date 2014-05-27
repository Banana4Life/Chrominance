package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.ProjectileLauncher;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.component.entity.TowerRender;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Tower extends EntityType {
    public Tower() {
        add(TowerRender.class);
        add(ProjectileLauncher.class);
        add(ColorContainer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(TowerRender.class);
        e.get(ProjectileLauncher.class)
                .setCooldown(1500, MILLISECONDS)
                .setTargetRange(100)
                .setProjectile(EntityTypes.BULLET);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE)
                .setAmount(1);
    }
}
