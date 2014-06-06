package de.cubeisland.games.level;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Node> nodes;
    private final Node spawn;
    private final Node target;

    public Path(MapStructure map, List<Node> nodes) {
        this.nodes = new ArrayList<>();
        for (Node node : nodes) {
            this.nodes.add(new Node(map.offset(map.scale(node.getLocation()))));
        }

        this.spawn = this.nodes.get(0);
        this.target = this.nodes.get(nodes.size() - 1);
    }

    public List<Node> getNodes() {
        return nodes;
    }



    public Node getSpawn() {
        return spawn;
    }

    public Node getTarget() {
        return target;
    }
}