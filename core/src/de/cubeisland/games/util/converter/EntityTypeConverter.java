package de.cubeisland.games.util.converter;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.engine.reflect.codec.ConverterManager;
import de.cubeisland.engine.reflect.codec.converter.Converter;
import de.cubeisland.engine.reflect.exception.ConversionException;
import de.cubeisland.engine.reflect.node.ListNode;
import de.cubeisland.engine.reflect.node.Node;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.entity.type.Enemy;
import de.cubeisland.games.entity.type.Runner;
import de.cubeisland.games.entity.type.Walker;
import de.cubeisland.games.wave.WaveStructure;

import java.util.ArrayList;
import java.util.List;

public class EntityTypeConverter implements Converter<EntityType> {
    @Override
    public Node toNode(EntityType object, ConverterManager manager) throws ConversionException {
        return null;
    }

    @Override
    public EntityType fromNode(Node node, ConverterManager manager) throws ConversionException {
        String[] strings = ((String) node.getValue()).toLowerCase().split(" ");
        EntityType type = new Runner();
        switch(strings[0]) {
            case "runner": type = new Runner(); break;
            case "walker": type = new Walker(); break;
        }
        switch (strings[1]) {
            case "red": ((Enemy) type).setColor(Color.RED); break;
            case "green": ((Enemy) type).setColor(Color.GREEN); break;
            case "blue": ((Enemy) type).setColor(Color.BLUE); break;
        }
        if (strings.length > 2) {
            ((Enemy) type).setShield(true);
        } else {
            ((Enemy) type).setShield(false);
        }
        return type;
    }
}
