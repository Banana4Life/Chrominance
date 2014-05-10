package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.MOVEMENT;

@Phase(MOVEMENT)
public class Move extends Component<Entity>
{
    @Override
    public void update(float delta) {
        getOwner().getLocation().add(getOwner().getVelocity().cpy().scl(delta));
    }
}
