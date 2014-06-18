package de.cubeisland.games.component.level;

import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.MapStructure;
import de.cubeisland.games.level.Path;
import de.cubeisland.games.wave.PredefinedWaveGenerator;
import de.cubeisland.games.wave.Wave;
import de.cubeisland.games.wave.WaveGenerator;

import java.util.concurrent.TimeUnit;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.util.VectorUtil.zero;

@Phase(BEGIN)
public class WaveController extends Component<Level> {
    private Wave currentWave;
    private long delay = 1000;
    private long timeWaited = this.delay;

    @Override
    public void update(float delta) {
        MapStructure map = getOwner().getMap();
        if (this.currentWave == null && !hasFinished()) {
            this.currentWave = map.getGenerator().generate(getOwner().getEntityFactory(), 0, map);
        }
        else if (this.currentWave.isCompleted() && !hasFinished() && ((PredefinedWaveGenerator)map.getGenerator()).getWaveStructure().hasWaveAfter(currentWave.getNumber())) {
            this.currentWave = map.getGenerator().generate(getOwner().getEntityFactory(), currentWave.getNumber() + 1, map);
        }

        timeWaited += (int) (delta * 1000 + .5f);
        if (timeWaited >= delay) {
            if (currentWave.hasMoreEntities()) {
                timeWaited = 0;
                this.spawnEnemy(currentWave.nextEntity());
            }
        }
    }

    private void spawnEnemy(Entity entity) {
        final Vector2 spawnLoc = entity.get(PathFollower.class).getPath().getSpawn().getLocation();
        getOwner().spawn(entity, spawnLoc);
        entity.setVelocity(zero());
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
        return getCurrentWave().isCompleted() && !((PredefinedWaveGenerator)getOwner().getMap().getGenerator()).getWaveStructure().hasWaveAfter(getCurrentWave().getNumber());
    }

    private Wave getCurrentWave() {
        if (this.currentWave == null) {
            this.currentWave = getOwner().getMap().getGenerator().generate(getOwner().getEntityFactory(), 0, getOwner().getMap());
        }
        return currentWave;
    }
}
