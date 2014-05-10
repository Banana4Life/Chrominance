package de.cubeisland.games.entity;

import de.cubeisland.games.entity.type.Bullet;
import de.cubeisland.games.entity.type.Player;
import de.cubeisland.games.entity.type.Runner;
import de.cubeisland.games.entity.type.Tower;


public abstract class EntityTypes {

    private EntityTypes() {
    }

    public static final EntityType RUNNER = new Runner();
    public static final Tower TOWER = new Tower();
    public static final Player PLAYER = new Player();

    public static final Bullet BULLET = new Bullet();
}