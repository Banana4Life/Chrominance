package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.ColorPalette;
import de.cubeisland.games.entity.type.ColorRepo;
import de.cubeisland.games.entity.type.Runner;
import de.cubeisland.games.entity.type.Walker;


public abstract class EntityTypes {

    private EntityTypes() {
    }

    public static final EntityType RUNNER = new Runner();
    public static final EntityType WALKER = new Walker();
    public static final ColorPalette COLOR_PALETTE = new ColorPalette();
    public static final ColorRepo COLOR_REPO = new ColorRepo();
}