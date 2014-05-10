package de.cubeisland.games.component.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.entity.PathFollower;
import de.cubeisland.games.component.entity.Render;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.level.Level;
import de.cubeisland.games.level.Map;
import de.cubeisland.games.level.Path;
import de.cubeisland.games.wave.Difficulty;
import de.cubeisland.games.wave.Wave;
import de.cubeisland.games.wave.WaveGenerator;

import static de.cubeisland.games.component.TickPhase.BEGIN;
import static de.cubeisland.games.util.VectorUtil.zero;

@Phase(BEGIN)
public class WaveController extends Component<Level> {
    private Difficulty difficulty = Difficulty.NORMAL;
    private WaveGenerator generator;
    private Wave currentWave;

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
        return this;
    }

    @Override
    public void update(float delta) {
        Map map = getOwner().getMap();

        if (this.currentWave == null || this.currentWave.isCompleted()) {
            int num = this.currentWave == null ? 0 : this.currentWave.getNumber();
            this.currentWave = this.generator.generate(getOwner().getEntityFactory(), num + 1, difficulty);
            for (Entity entity : this.currentWave.getEntities()) {
                Path path = map.getRandomPath();
                Vector2 spawnLoc = path.getSpawn().getLocation();
                getOwner().spawn(entity, spawnLoc);
                entity.setVelocity(zero());
                entity.get(Render.class).setColor(Color.DARK_GRAY);
                entity.get(PathFollower.class).setPath(path);
            }
        }
    }
}
