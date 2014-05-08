package de.cubeisland.games.entity;

import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.type.Enemy;
import de.cubeisland.games.entity.type.Player;
import de.cubeisland.games.entity.type.Runner;
import de.cubeisland.games.entity.type.Tower;

import java.util.List;

/**
 * Created by Jonas on 28.04.14.
 */
public abstract class EntityTypes {
    public static final EntityType RUNNER = new Runner();
    public static Tower TOWER = new Tower();
    public static Player PLAYER = new Player();
    public static Enemy ENEMY = new Enemy();
}