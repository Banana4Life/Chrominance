package de.cubeisland.games.level.tile;

import java.util.HashMap;
import java.util.Map;

public enum TileType {
    PALETTE_POSITION(0xFF00FFFF), // MAGENTA
    BEGIN_PATH(0x2AFF00FF),
    PATH(0x000000FF),
    END_PATH(0xFF0000FF),
    WALL(0xFFFFFFFF),
    NONE(0x00000000);

    private static final Map<Integer, TileType> COLOR_VALUE_MAP = new HashMap<>();
    private final int colorValue;

    TileType(int colorValue) {
        this.colorValue = colorValue;
    }

    public int getColorValue() {
        return colorValue;
    }

    public static TileType getByColorValue(int colorValue) {
        TileType type = COLOR_VALUE_MAP.get(colorValue);
        if (type == null) {
            return NONE;
        }
        return type;
    }

    static {
        for (TileType type : values()) {
            COLOR_VALUE_MAP.put(type.getColorValue(), type);
        }
    }
}
