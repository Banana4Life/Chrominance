package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.entity.Health;
import de.cubeisland.games.component.entity.PaletteRenderer;
import de.cubeisland.games.component.entity.PlayerInput;
import de.cubeisland.games.entity.EntityType;

/**
 * Created by jonas on 30.04.14.
 */
public class ColorPalette extends EntityType {
    public ColorPalette() {
        this.add(PlayerInput.class);
        this.add(PaletteRenderer.class);
    }
}
