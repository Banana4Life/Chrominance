package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class Render extends Component<Entity>
{
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Color color = Color.WHITE;

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation();

        this.shapeRenderer.begin(Filled);
        this.shapeRenderer.setColor(this.color);
        this.shapeRenderer.circle(loc.x, loc.y, 20);
        this.shapeRenderer.end();
    }
}
