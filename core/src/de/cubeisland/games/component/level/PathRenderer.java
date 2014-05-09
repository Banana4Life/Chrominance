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
        List<Path> paths = getOwner().getPaths();
        for (int i = 0; i < paths.size(); ++i)
        {
            nodes = paths.get(i).getNodes();
            lastNode = nodes.get(0);
            for (int j = 1; j < nodes.size(); j++)
            {
                currNode = nodes.get(j);

                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.line(currNode.getLocation(), lastNode.getLocation());
                sr.end();

                lastNode = currNode;
            }
        }
    }
}
