package de.cubeisland.games.component.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.component.After;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.Node;
import de.cubeisland.games.level.Path;

import java.util.List;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@After(GridRenderer.class)
@Phase(RENDERING)
public class PathRenderer extends Component<Level>
{
    private final ShapeRenderer sr = new ShapeRenderer();

    @Override
    public void update(float delta)
    {
        List<Node> nodes;
        Node lastNode;
        Node currNode;
        List<Path> paths = getOwner().getMap().getPaths();
        for (Path path : paths) {
            nodes = path.getNodes();
            lastNode = nodes.get(0);
            for (int j = 1; j < nodes.size(); j++) {
                currNode = nodes.get(j);

                /* sr.setColor(Color.LIGHT_GRAY);
                sr.begin(Filled);
                sr.rectLine(currNode.getLocation(), lastNode.getLocation(), 30);
                sr.end(); */ // Maybe paint the paths like that

                sr.setColor(Color.RED);
                sr.begin(Line);
                sr.line(currNode.getLocation(), lastNode.getLocation());
                sr.end();

                lastNode = currNode;
            }
        }
    }
}
