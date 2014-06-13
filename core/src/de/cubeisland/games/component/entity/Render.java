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
    private ShapeRenderer renderer;
    private Color color = Color.WHITE;
    private float radius = 10;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation();

        if (getOwner().has(ColorContainer.class)) {
            this.color = getOwner().get(ColorContainer.class).getColor();
        }

        this.renderer.begin(Filled);
        this.renderer.setColor(this.color);
        this.renderer.circle(loc.x, loc.y, radius);
        this.renderer.end();

        if (getOwner().has(ProjectileLauncher.class)) {
            this.renderer.begin(Line);
            this.renderer.setColor(Color.RED);
            this.renderer.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
            this.renderer.end();
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
