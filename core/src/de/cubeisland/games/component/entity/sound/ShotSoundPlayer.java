package de.cubeisland.games.component.entity.sound;

import de.cubeisland.games.component.entity.ProjectileLauncher;

import static de.cubeisland.games.component.entity.ProjectileLauncher.ProjectileLaunchEvent;

public class ShotSoundPlayer extends SoundPlayer {

    public void handle(ProjectileLauncher launcher, ProjectileLaunchEvent event) {
        play();
    }
}
