package de.cubeisland.games.entity.type;

import de.cubeisland.games.component.PlayerInput;
import de.cubeisland.games.entity.EntityType;

/**
 * Created by jonas on 30.04.14.
 */
public class Player extends EntityType {
    public Player() {
        this.add(PlayerInput.class);
    }
}
