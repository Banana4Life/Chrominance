package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.ReflectedYaml;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.entity.type.Tower;
import de.cubeisland.games.level.TileMapWithPathsAndTowerLocations;

import java.lang.reflect.Field;
import java.util.List;

public class TowerManager extends ResourceManager<Tower> {
    public Tower towerSlow;
    public Tower towerPower;
    public Tower towerHighFreq;
    public Tower towerRange;
    public Tower towerPoison;

    public TowerManager(Chrominance game) {
        super(game, "./tower");
    }

    @Override
    protected Tower makeResource(Field field, FileHandles fileMap) {
        final String fieldName = field.getName();
        final Texture baseTexture = new Texture(fileMap.get(fieldName + "Base", "towerBase"));
        final Texture turretTexture = new Texture(fileMap.get(fieldName + "Turret"));

        TowerConfig config = this.getGame().getReflector().load(TowerConfig.class, fileMap.get(fieldName + "Config").read());

        return new Tower()
                    .setCenterOffset(config.centerOffset)
                    .setMuzzleOffset(config.muzzleOffset)
                    .setTurretTexture(turretTexture)
                    .setBaseTexture(baseTexture)
                    .setMaxShots(config.maxShots)
                    .setMaxRotationPerTick(config.maxRotationPerTick)
                    .setTargetRange(config.targetRange)
                    .setCooldown(config.cooldown)
                    .addColorTowerMap(Color.rgba8888(config.mapIndicatorColor));
    }

    public static class TowerConfig extends ReflectedYaml {
        public List<Vector2> muzzleOffset;
        public Vector2 centerOffset;
        public float maxShots;
        public float maxRotationPerTick;
        public float targetRange;
        public long cooldown;
        public Color mapIndicatorColor;
    }
}
