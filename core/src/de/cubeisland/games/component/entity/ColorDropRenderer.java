package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.ColorRepoValue;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
@Require(ColorRepoValue.class)
public class ColorDropRenderer extends Component<Entity> {

    private ShapeRenderer renderer;
    private ColorRepoValue value;
    private float radius = 5f;

    @Override
    protected void onInit() {
        super.onInit();

        this.renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
        this.value = getOwner().get(ColorRepoValue.class);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void update(float delta) {

        Vector2 pos = getOwner().getLocation();

        this.renderer.begin(Filled);
        this.renderer.setColor(this.value.getComponent().getColor(255));
        this.renderer.circle(pos.x, pos.y, this.radius);
        this.renderer.end();
        this.renderer.begin(Line);
        this.renderer.setColor(Color.WHITE);
        this.renderer.circle(pos.x, pos.y, this.radius);
        this.renderer.end();
    }
}
