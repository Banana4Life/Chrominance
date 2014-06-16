package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.entity.*;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.event.EventSender;
import de.cubeisland.games.ui.event.TouchUpEvent;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.component.entity.ClickBounds.RectangularBound;
import static de.cubeisland.games.component.entity.FingerInputDetector.EntityTouchedEvent;

@Phase(BEGIN)
public class ColorRepo extends EntityType {
    public ColorRepo() {
        add(FingerInputDetector.class);
        add(ColorRepoValue.class);
        add(ColorRepoRenderer.class);
        add(ColorDropSpawner.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        float scale = e.getLevel().getMap().getScale();
        e.get(ClickBounds.class)
                .setBoundShape(new RectangularBound(scale * .75f, scale));
    }
}
