package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
@Require(Spawner.class)
public class ColorDropRenderer extends Component<Entity> {

    private ShapeRenderer renderer;
    private Color color;
    private float radius = 5f;

    @Override
    protected void onInit() {
        super.onInit();

        this.renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

    public float getRadius() {
        return radius;
    }

    public ColorDropRenderer setColor(Color color) {
        this.color = color.cpy();
        return this;
    }

    @Override
    public void update(float delta) {

        Vector2 pos = getOwner().getLocation();

        if (this.color == null) {
            this.color = getOwner().get(Spawner.class).get().get(ColorContainer.class).getColor();
        }

        this.renderer.begin(Filled);
        this.renderer.setColor(this.color);
        this.renderer.circle(pos.x, pos.y, this.radius);
        this.renderer.end();
        this.renderer.begin(Line);
        this.renderer.setColor(Color.WHITE);
        this.renderer.circle(pos.x, pos.y, this.radius);
        this.renderer.end();
    }
}
