package de.cubeisland.games.level;

import com.badlogic.gdx.math.Vector2;

import java.util.Collections;
import java.util.List;

public class Path {
    private final List<Node> nodes;
    private final Node spawn;
    private final Node target;

    public Path(List<Node> nodes) {
        this.nodes = nodes;
        this.spawn = nodes.get(0);
        this.target = nodes.get(nodes.size() - 1);
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public Node getSpawn() {
        return spawn;
    }

    public Node getTarget() {
        return target;
    }
}
