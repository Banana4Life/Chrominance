package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.RENDERING;


@Phase(RENDERING)
public class PaletteRenderer extends Component<Entity> {
    private final ShapeRenderer sr = new ShapeRenderer();
    private float width = 10;
    private float height = 10;

    @Override
    public void update(float delta) {

    }
}
