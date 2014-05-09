package de.cubeisland.games.level.tile;

import java.util.HashMap;
import java.util.Map;

public enum TileType {
    TOWER_SLOT(-2004317953),
    BEGIN_PATH(721355007),
    PATH(255),
    END_PATH(-16776961),
    WALL(-1),
    NONE(0);

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
