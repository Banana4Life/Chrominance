package de.cubeisland.games.component.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.After;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.Node;
import de.cubeisland.games.level.Path;
import de.cubeisland.games.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@After(GridRenderer.class)
@Phase(RENDERING)
public class PathRenderer extends Component<Level>
{
    private ShapeRenderer renderer;
    private Color color = Color.LIGHT_GRAY;
    private Color targetColor = Color.RED;
    private float width = 30;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta)
    {
        List<Node> nodes;
        Vector2 previousNode;
        Vector2 currNode;
        List<Path> paths = getOwner().getMap().getPaths();

        Set<Pair<Vector2, Vector2>> lines = new HashSet<>();

        for (Path path : paths) {
            nodes = path.getNodes();
            previousNode = path.getSpawn().getLocation();
            for (int j = 1; j < nodes.size(); j++) {
                currNode = nodes.get(j).getLocation();
                lines.add(new Pair<>(previousNode, currNode));
                previousNode = currNode;
            }
        }

        if (lines.isEmpty()) {
            return;
        }

        Vector2 left, right;
        for (Pair<Vector2, Vector2> line : lines) {
            left = line.getLeft();
            right = line.getRight();

            renderer.begin(Filled);
            renderer.setColor(this.color);
            renderer.rectLine(left, right, this.width);
            renderer.circle(left.x, left.y, this.width / 2f, (int) (this.width / 2f + .5));
            renderer.end();
        }

        Vector2 target = paths.get(0).getTarget().getLocation();
        final float r = this.width / 2f;
        final int segments = Math.round(r / 2);

        renderer.begin(Filled);
        renderer.setColor(Color.RED);
        renderer.circle(target.x, target.y, r + 6, segments);
        renderer.setColor(Color.GREEN);
        renderer.circle(target.x, target.y, r + 3, segments);
        renderer.setColor(Color.BLUE);
        renderer.circle(target.x, target.y, r, segments);
        renderer.end();
    }

    public Color getColor() {
        return color;
    }

    public PathRenderer setColor(Color color) {
        this.color = color;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public PathRenderer setWidth(float width) {
        this.width = width;
        return this;
    }
}
