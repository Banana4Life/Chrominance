package de.cubeisland.games.util.converter;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.engine.reflect.codec.ConverterManager;
import de.cubeisland.engine.reflect.codec.converter.Converter;
import de.cubeisland.engine.reflect.exception.ConversionException;
import de.cubeisland.engine.reflect.node.ListNode;
import de.cubeisland.engine.reflect.node.Node;

import java.util.List;

public class ColorConverter implements Converter<Color> {
    @Override
    public Node toNode(Color object, ConverterManager manager) throws ConversionException {
        ListNode node = ListNode.emptyList();
        node.addNode(Node.wrapIntoNode(object.r));
        node.addNode(Node.wrapIntoNode(object.g));
        node.addNode(Node.wrapIntoNode(object.b));
        node.addNode(Node.wrapIntoNode(object.a));
        return node;
    }

    @Override
    public Color fromNode(Node node, ConverterManager manager) throws ConversionException {
        if (!(node instanceof ListNode)) {
            throw new IllegalArgumentException("ListNode expected!");
        }
        List<Node> list = ((ListNode) node).getValue();
        if (list.size() != 4) {
            throw new IllegalArgumentException("The list must have exactly 4 elements!");
        }

        float r = (float) manager.convertFromNode(list.get(0), float.class) / 255f;
        float g = (float) manager.convertFromNode(list.get(1), float.class) / 255f;
        float b = (float) manager.convertFromNode(list.get(2), float.class) / 255f;
        float a = (float) manager.convertFromNode(list.get(3), float.class) / 255f;

        return new Color(r, g, b, a);
    }
}
