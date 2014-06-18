package de.cubeisland.games.component.entity.sound;

import com.badlogic.gdx.audio.Sound;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public abstract class SoundPlayer extends Component<Entity> {

    private Sound sound;
    private float volume = 1;

    public Sound getSound() {
        return sound;
    }

    public SoundPlayer setSound(Sound sound) {
        if (sound == null) {
            throw new IllegalArgumentException("Sound may not be null!");
        }
        this.sound = sound;
        return this;
    }

    public float getVolume() {
        return volume;
    }

    public SoundPlayer setVolume(float volume) {
        this.volume = volume;
        return this;
    }

    public void play() {
        this.sound.play(this.volume);
    }

    @Override
    public void update(float delta) {

    }
}
