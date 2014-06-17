package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.RENDERING;
import static de.cubeisland.games.component.entity.ClickBounds.BoundShape;
import static de.cubeisland.games.component.entity.ClickBounds.CircularBound;
import static de.cubeisland.games.component.entity.ClickBounds.RectangularBound;

@Phase(RENDERING)
public class ColorRepoRenderer extends Component<Entity> {

    private ShapeRenderer renderer;
    private ClickBounds clickBounds;
    private ColorContainer colorContainer;

    @Override
    protected void onInit() {
        super.onInit();

        renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
        clickBounds = getOwner().get(ClickBounds.class);
        colorContainer = getOwner().get(ColorContainer.class);
    }

    @Override
    public void update(float delta) {
        BoundShape bound = clickBounds.getBoundShape();
        Vector2 pos = getOwner().getLocation();

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(colorContainer.getColor());
        if (bound instanceof RectangularBound) {
            RectangularBound rect = (RectangularBound) bound;
            renderer.rect(pos.x, pos.y, rect.getWidth(), rect.getHeight());
        } else if (bound instanceof CircularBound) {
            renderer.circle(pos.x, pos.y, ((CircularBound) bound).getRadius());
        }

        renderer.end();
    }
}
