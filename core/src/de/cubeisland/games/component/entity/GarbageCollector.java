package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.After;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

@After(Move.class)
public class GarbageCollector extends Component<Entity> {
    @Override
    public void update(float delta) {
        Vector2 loc = getOwner().getLocation().cpy();

        if (loc.x < -100 || loc.x > Gdx.graphics.getWidth() + 100) {
            getOwner().die();
        }

        if (loc.y < -100 || loc.x > Gdx.graphics.getHeight() + 100) {
            getOwner().die();
        }
    }
}
