package de.cubeisland.games.component.level;

import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.Node;
import de.cubeisland.games.level.Path;

public class PathRenderer extends Component<Level>
{
    private final ShapeRenderer sr = new ShapeRenderer();

    @Override
    public void update(float delta)
    {
        List<Node> nodes;
        Node lastNode;
        Node currNode;
        for (Path path : getOwner().getPaths())
        {
            nodes = path.getNodes();
            lastNode = nodes.get(0);
            for (int n = 1; n < nodes.size(); n++)
            {
                currNode = nodes.get(n);

                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.line(currNode.getLocation(), lastNode.getLocation());
                sr.end();

                lastNode = currNode;
            }
        }
    }
}
