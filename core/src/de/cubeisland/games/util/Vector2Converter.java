package de.cubeisland.games.util;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.codec.ConverterManager;
import de.cubeisland.engine.reflect.codec.converter.Converter;
import de.cubeisland.engine.reflect.exception.ConversionException;
import de.cubeisland.engine.reflect.node.ListNode;
import de.cubeisland.engine.reflect.node.Node;

import java.util.List;

public class Vector2Converter implements Converter<Vector2> {
    @Override
    public Node toNode(Vector2 object, ConverterManager manager) throws ConversionException {
        ListNode node = ListNode.emptyList();
        node.addNode(Node.wrapIntoNode(object.x));
        node.addNode(Node.wrapIntoNode(object.y));
        return node;
    }

    @Override
    public Vector2 fromNode(Node node, ConverterManager manager) throws ConversionException {
        if (!(node instanceof ListNode)) {
            throw new IllegalArgumentException("ListNode expected!");
        }
        List<Node> list = ((ListNode) node).getValue();
        if (list.size() != 2) {
            throw new IllegalArgumentException("The list must have exactly 2 elements!");
        }

        float x = manager.convertFromNode(list.get(0), float.class);
        float y = manager.convertFromNode(list.get(1), float.class);

        return new Vector2(x, y);
    }
}
