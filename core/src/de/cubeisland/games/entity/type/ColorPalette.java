package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.entity.PaletteRenderer;
import de.cubeisland.games.component.entity.PlayerInput;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.level.Level;

import static de.cubeisland.games.component.ColorRepoValue.ColorComponent.BLUE;
import static de.cubeisland.games.component.ColorRepoValue.ColorComponent.GREEN;
import static de.cubeisland.games.component.ColorRepoValue.ColorComponent.RED;
import static de.cubeisland.games.entity.EntityTypes.COLOR_REPO;

public class ColorPalette extends EntityType {
    public ColorPalette() {
        add(PlayerInput.class);
        add(PaletteRenderer.class);
    }

    @Override
    protected void onSpawned(Entity e) {
        super.onSpawned(e);

        final Level level = e.getLevel();

        Entity red = level.spawn(COLOR_REPO, e.getLocation());
        Entity green = level.spawn(COLOR_REPO, e.getLocation().cpy().add(level.getMap().getScale(), 0));
        Entity blue = level.spawn(COLOR_REPO, e.getLocation().cpy().add(2 * level.getMap().getScale(), 0));

        red  .get(ColorRepoValue.class).setComponent(  RED);
        green.get(ColorRepoValue.class).setComponent(GREEN);
        blue .get(ColorRepoValue.class).setComponent( BLUE);
    }
}
