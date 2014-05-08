package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.Player;
import de.cubeisland.games.entity.type.Runner;
import de.cubeisland.games.entity.type.Tower;

public abstract class EntityTypes {
    public static final EntityType RUNNER = new Runner();
    public static Tower TOWER = new Tower();
    public static Player PLAYER = new Player();
}