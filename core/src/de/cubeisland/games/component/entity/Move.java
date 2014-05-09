package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class Move extends Component<Entity>
{
    @Override
    public void update(float delta) {
        getOwner().getLocation().add(getOwner().getVelocity().cpy().scl(delta));
    }
}
