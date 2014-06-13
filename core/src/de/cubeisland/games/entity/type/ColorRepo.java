package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.TickPhase;
import de.cubeisland.games.component.entity.ClickBounds;
import de.cubeisland.games.component.entity.ClickKiller;
import de.cubeisland.games.component.entity.ColorRepoRenderer;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.component.entity.ClickBounds.RectangularBound;

@Phase(BEGIN)
public class ColorRepo extends EntityType {
    public ColorRepo() {
        add(ClickKiller.class);
        add(ColorRepoValue.class);
        add(ColorRepoRenderer.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        float scale = e.getLevel().getMap().getScale();
        e.get(ClickBounds.class).setBoundShape(new RectangularBound(scale * .75f, scale));
    }
}
