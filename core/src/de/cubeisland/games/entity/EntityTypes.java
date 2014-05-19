package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.*;


public abstract class EntityTypes {

    private EntityTypes() {
    }

    public static final EntityType RUNNER = new Runner();
    public static final EntityType WALKER = new Walker();
    public static final Tower TOWER = new Tower();
    public static final ColorPalette COLOR_PALETTE = new ColorPalette();

    public static final Bullet BULLET = new Bullet();
}