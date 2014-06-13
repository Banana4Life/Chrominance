package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.entity.ClickBounds;
import de.cubeisland.games.component.entity.ClickKiller;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;

import static de.cubeisland.games.component.entity.ClickBounds.RectangularBound;

public class ColorRepo extends EntityType {
    public ColorRepo() {
        add(ClickKiller.class);
        add(ColorRepoValue.class);
    }

    @Override
    protected void onInitialize(Entity e) {
        e.get(ClickBounds.class).setBoundShape(new RectangularBound(10, 10));
    }
}
