package de.cubeisland.games.component.entity.sound;

import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.entity.Entity.EntityDeathEvent;

public class DeathSoundPlayer extends SoundPlayer {
    public void handle(Entity entity, EntityDeathEvent event) {
        play();
    }
}
