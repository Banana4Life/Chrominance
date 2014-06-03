package de.cubeisland.games.component.entity;

import com.badlogic.gdx.audio.Sound;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

import static de.cubeisland.games.component.entity.ProjectileLauncher.ProjectileLaunchEvent;

public class SoundPlayer extends Component<Entity> {

    private Sound sound;

    @Override
    public void update(float delta) {
    }

    public void handle(ProjectileLauncher launcher, ProjectileLaunchEvent event) {
        getSound().play();
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        if (sound == null) {
            throw new IllegalArgumentException("Sound may not be null!");
        }
        this.sound = sound;
    }
}
