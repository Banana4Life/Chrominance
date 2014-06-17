package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.component.entity.ClickBounds.RectangularBound;

@Phase(BEGIN)
public class ColorRepo extends EntityType {
    public ColorRepo() {
        add(FingerInputDetector.class);
        add(ColorContainer.class);
        add(ColorRepoRenderer.class);
        add(ColorDropSpawner.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        float scale = e.getLevel().getMap().getScale();
        e.get(ClickBounds.class)
                .setBoundShape(new RectangularBound(scale * .75f, scale));
        e.get(ColorContainer.class)
                .setMaxAmount(1000)
                .setAmount(1000);
    }
}
