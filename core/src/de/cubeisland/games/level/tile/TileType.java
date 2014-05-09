package de.cubeisland.games.level.tile;

/**
 * Created by jonas on 30.04.14.
 */
public enum TileType {
    TOWERSLOT,
    BEGIN_PATH,
    PATH,
    END_PATH,
    WALL,
    NONE;

    public static TileType getType(int colorValue) {
        switch (colorValue) {
            case -2004317953:
                return TOWERSLOT;
            case -16776961:
                return END_PATH;
            case -1:
                return WALL;
            case 255:
                return PATH;
            case 721355007:
                return BEGIN_PATH;
            default:
                return NONE;
        }
    }
}
