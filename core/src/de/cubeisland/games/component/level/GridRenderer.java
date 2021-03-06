package de.cubeisland.games.component.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.MapStructure;

import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
public class GridRenderer extends Component<Level>
{
    private ShapeRenderer renderer;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta)
    {
        final MapStructure map = getOwner().getMap();
        final float scale = map.getScale();
        final Vector2 offset = map.getOffset();

        this.renderer.begin(ShapeType.Line);
        this.renderer.setColor(Color.GREEN);

        // horizontal lines
        for (int i = 1; i < map.getHeight(); ++i)
        {
            float y = Gdx.graphics.getHeight() - (i * scale) - offset.y;
            this.renderer.line(0, y, Gdx.graphics.getWidth() - 1, y);
        }

        // vertical lines
        for (int i = 1; i < map.getWidth(); ++i)
        {
            float x = Gdx.graphics.getWidth() - (i * scale) - offset.x;
            this.renderer.line(x, Gdx.graphics.getHeight() - 1, x, 0);
        }

        this.renderer.end();
    }
}
