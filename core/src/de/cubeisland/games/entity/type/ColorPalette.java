package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.entity.PaletteRenderer;
import de.cubeisland.games.component.entity.PlayerInput;
import de.cubeisland.games.entity.EntityType;

public class ColorPalette extends EntityType {
    public ColorPalette() {
        this.add(PlayerInput.class);
        this.add(PaletteRenderer.class);
    }
}