package de.cubeisland.games.resourcemanager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.ReflectedYaml;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.entity.type.Tower;

import java.lang.reflect.Field;
import java.util.List;

public class TowerManager extends ResourceManager<Tower> {
    public Tower slow;
    public Tower power;
    public Tower highfreq;
    public Tower range;
    public Tower poison;

    private final Reflector reflector;

    public TowerManager(Reflector reflector) {
        super("tower");
        this.reflector = reflector;
    }

    @Override
    protected Tower makeResource(FileHandle basedir, Field field) {

        FileHandle towerDir = fieldToFileHandle(field, basedir);
        final Texture baseTexture = new Texture(basedir.child("base.png"));
        final Texture turretTexture = new Texture(towerDir.child("turret.png"));

        TowerConfig config = this.reflector.load(TowerConfig.class, towerDir.child("config.yml").read());

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
