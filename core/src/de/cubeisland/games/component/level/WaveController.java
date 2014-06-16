package de.cubeisland.games.component.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.Path;
import de.cubeisland.games.wave.Difficulty;
import de.cubeisland.games.wave.Wave;
import de.cubeisland.games.wave.WaveGenerator;

import java.util.concurrent.TimeUnit;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.util.VectorUtil.zero;

@Phase(BEGIN)
public class WaveController extends Component<Level> {
    private Difficulty difficulty = Difficulty.NORMAL;
    private WaveGenerator generator;
    private Wave currentWave;
    private long remainingWaves;
    private long delay = 1000;
    private long timeWaited = this.delay;

    public WaveGenerator getGenerator() {
        return generator;
    }

    public WaveController setGenerator(WaveGenerator generator) {
        this.generator = generator;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public WaveController setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.remainingWaves = difficulty.getWaveCount();
        return this;
    }

    public long getRemainingWaves() {
        return remainingWaves;
    }

    @Override
    public void update(float delta) {
        if ((this.currentWave == null || this.currentWave.isCompleted()) && remainingWaves > 0) {
            int num = this.currentWave == null ? 0 : this.currentWave.getNumber();
            this.currentWave = this.generator.generate(getOwner().getEntityFactory(), num + 1);
            remainingWaves--;
        } else if (remainingWaves <= 0) {
            // WIN!!! Yeah
        }

        timeWaited += (int) (delta * 1000 + .5f);
        if (timeWaited >= delay) {
            if (currentWave.hasMoreEntities()) {
                timeWaited -= delay;
                this.spawnEnemy(currentWave.nextEntity());
            }
        }
    }

    private void spawnEnemy(Entity entity) {
        final Path path = getOwner().getMap().getRandomPath();
        final Vector2 spawnLoc = path.getSpawn().getLocation();
        getOwner().spawn(entity, spawnLoc);
        entity.setVelocity(zero());
        entity.get(PathFollower.class).setPath(path);
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.setDelay(delay, TimeUnit.MILLISECONDS);
    }

    public void setDelay (long delay, TimeUnit unit) {
        this.delay = unit.toMillis(delay);
    }

    public boolean hasFinished() {
        return (remainingWaves > 0) ? false : true;
    }
}
