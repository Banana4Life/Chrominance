package de.cubeisland.games.level.tile;

/**
 * Created by jonas on 30.04.14.
 */
public enum TileType {
    TOWERSLOT,
    WAY,
    WALL,
    NONE;

    public static TileType getType(int colorValue) {
        switch (colorValue) {
            case -2004317953:
                return TOWERSLOT;
            case -1:
                return WALL;
            case 255:
                return WAY;
            default:
                return NONE;
        }
    }
}
