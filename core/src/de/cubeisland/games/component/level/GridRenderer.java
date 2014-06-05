package de.cubeisland.games.component.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.TileMapWithPathsAndTowerLocations;

import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
public class GridRenderer extends Component<Level>
{
    private final ShapeRenderer sr = new ShapeRenderer();

    @Override
    protected void onInit()
    {
        this.sr.setColor(Color.GREEN);
    }

    @Override
    public void update(float delta)
    {
        final TileMapWithPathsAndTowerLocations map = getOwner().getMap();
        final float cellWidth = map.getScale();
        final float cellHeight = map.getScale();
        final float xOffset = map.getXOffset();
        final float yOffset = map.getYOffset();

        // horizontal lines
        for (int i = 1; i < map.getHeight(); ++i)
        {
            float y = Gdx.graphics.getHeight() - (i * cellHeight) - yOffset;
            this.drawLine(0, y, Gdx.graphics.getWidth() - 1, y);
        }

        // vertical lines
        for (int i = 1; i < map.getWidth(); ++i)
        {
            float x = Gdx.graphics.getWidth() - (i * cellWidth) - xOffset;
            this.drawLine(x, Gdx.graphics.getHeight() - 1, x, 0);
        }
    }

    private void drawLine(float fromX, float fromY, float toX, float toY) {
        this.sr.begin(ShapeType.Line);
        this.sr.line(fromX, fromY, toX, toY);
        this.sr.end();
    }
}
