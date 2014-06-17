package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public abstract class EntityTypes {

    private EntityTypes() {
    }

    private static final Map<String, EntityType> TYPE_LOOKUP = new HashMap<>();

    public static final EntityType RUNNER = new Runner();
    public static final EntityType WALKER = new Walker();
    public static final ColorPalette COLOR_PALETTE = new ColorPalette();
    public static final ColorRepo COLOR_REPO = new ColorRepo();
    public static final ColorDrop COLOR_DROP = new ColorDrop();

    static {
        for (Field f : EntityTypes.class.getFields()) {
            if (Modifier.isStatic(f.getModifiers()) && EntityType.class.isAssignableFrom(f.getType()) && f.isAccessible()) {
                try {
                    TYPE_LOOKUP.put(f.getName(), EntityType.class.cast(f.get(null)));
                } catch (IllegalAccessException e) {
                    System.err.println("Failed to read in a field for the entity type lookup table:");
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static EntityType getTypeByName(String name) {
        return TYPE_LOOKUP.get(name.replace(" ", "_").toUpperCase());
    }
}