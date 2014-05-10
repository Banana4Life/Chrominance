package de.cubeisland.games.component.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.TickPhase.BEGIN;

@Phase(BEGIN)
public class PlayerInput extends Component<Entity>
{
    @Override
    public void update(float delta) {
        System.out.println("Input!");
    }
}
