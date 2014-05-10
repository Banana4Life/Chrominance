package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
public class Render extends Component<Entity>
{
    private final ShapeRenderer sr = new ShapeRenderer();
    private Color color = Color.WHITE;
    private float radius = 10;

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation();

        this.sr.begin(Filled);
        this.sr.setColor(this.color);
        this.sr.circle(loc.x, loc.y, radius);
        this.sr.end();

        if (getOwner().has(ProjectileLauncher.class)) {
            this.sr.begin(Line);
            this.sr.setColor(Color.RED);
            this.sr.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
            this.sr.end();
        }
    }

    public Color getColor() {
        return color;
    }

    public Render setColor(Color color) {
        this.color = color;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public Render setRadius(float radius) {
        this.radius = radius;
        return this;
    }
}
