package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.After;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.MOVEMENT;
import static de.cubeisland.games.component.entity.PathFollower.PathCompleteEvent;

@After(Move.class)
@Phase(MOVEMENT)
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

    public void handle(Component sender, PathCompleteEvent event) {
        System.out.println("Path completed!");
        getOwner().die();
    }
}
