package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Before;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

@Before(Move.class)
public class PathFollower extends Component<Entity>
{
    @Override
    public void update(float delta) {

    }
}
