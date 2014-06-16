package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.*;


public abstract class EntityTypes {

    private EntityTypes() {
    }

    public static final EntityType RUNNER = new Runner();
    public static final EntityType WALKER = new Walker();
    public static final ColorPalette COLOR_PALETTE = new ColorPalette();
    public static final ColorRepo COLOR_REPO = new ColorRepo();
    public static final ColorDrop COLOR_DROP = new ColorDrop();
}