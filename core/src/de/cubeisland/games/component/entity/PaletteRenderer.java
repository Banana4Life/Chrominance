package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.RENDERING;


@Phase(RENDERING)
public class PaletteRenderer extends Component<Entity> {
    private final ShapeRenderer renderer = new ShapeRenderer();
    private float width = 3;
    private float height = 2;
    private final Color background = Color.valueOf("C8C8C8");
    private final Color border = Color.valueOf("B8B8B8");

    @Override
    protected void onInit() {
        super.onInit();
        float scale = getOwner().getLevel().getMap().getScale();
        this.width *= scale;
        this.height *= scale;

    }

    public void update(float delta) {
        Vector2 pos = getOwner().getLocation();

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(background);
        renderer.rect(pos.x, pos.y, width, height);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(border);
        renderer.rect(pos.x, pos.y, width, height);
        renderer.end();
    }
}
