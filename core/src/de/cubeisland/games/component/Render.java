package de.cubeisland.games.component;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.entity.Entity;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class Render extends Component<Entity> {

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation().cpy();

        this.shapeRenderer.begin(Filled);
        this.shapeRenderer.circle(loc.x, loc.y, 20);
        this.shapeRenderer.end();
    }
}
