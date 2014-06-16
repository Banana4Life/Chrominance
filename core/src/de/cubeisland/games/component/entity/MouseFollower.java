package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.MOVEMENT;
import static de.cubeisland.games.screen.GameScreenInputProcessor.unproject;

@Phase(MOVEMENT)
public class MouseFollower extends Component<Entity> {
    @Override
    public void update(float delta) {
        Vector3 pos = unproject(getOwner().getLevel().getScreen().getGame().getCamera(), Gdx.input.getX(), Gdx.input.getY());
        getOwner().setLocation(new Vector2(pos.x, pos.y));
    }
}
