package de.cubeisland.games.util.converter;

import de.cubeisland.engine.reflect.codec.ConverterManager;
import de.cubeisland.engine.reflect.codec.converter.Converter;
import de.cubeisland.engine.reflect.exception.ConversionException;
import de.cubeisland.engine.reflect.node.ListNode;
import de.cubeisland.engine.reflect.node.Node;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;
import de.cubeisland.games.wave.WaveStructure;

import java.util.List;

public class EntityTypeConverter implements Converter<EntityType> {
    @Override
    public Node toNode(EntityType object, ConverterManager manager) throws ConversionException {
        return null;
    }

    @Override
    public EntityType fromNode(Node node, ConverterManager manager) throws ConversionException {
        return EntityTypes.getTypeByName((String) node.getValue());
    }
}
