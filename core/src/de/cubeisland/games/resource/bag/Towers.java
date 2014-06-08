package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.engine.reflect.ReflectedYaml;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.entity.type.Projectile;
import de.cubeisland.games.entity.type.Tower;
import de.cubeisland.games.resource.ResourceBag;

import java.lang.reflect.Field;
import java.util.List;

public class Towers extends ResourceBag<Tower> {
    public Tower slow;
    public Tower power;
    public Tower highfreq;
    public Tower range;
    public Tower poison;

    private final Reflector reflector;
    private final Projectiles projectiles;

    public Towers(Reflector reflector, Projectiles projectiles) {
        this.reflector = reflector;
        this.projectiles = projectiles;
    }

    @Override
    protected Tower load(FileHandle basedir, Field field) {

        FileHandle towerDir = fieldToFileHandle(field, basedir);
        final Texture baseTexture = new Texture(basedir.child("base.png"));
        final Texture turretTexture = new Texture(towerDir.child("turret.png"));

        TowerConfig config = this.reflector.load(TowerConfig.class, towerDir.child("config.yml").read());

        Projectile projectile = null;
        try {
            projectile = (Projectile) projectiles.getClass().getField(config.projectileName).get(projectiles);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Gdx.app.log("Error loading projectile of tower" + field.getName(), e.getMessage());
        }

        return new Tower(config.mapIndicatorColor)
                .setCenterOffset(config.centerOffset)
                .setMuzzleOffset(config.muzzleOffset)
                .setTurretTexture(turretTexture)
                .setBaseTexture(baseTexture)
                .setMaxShots(config.maxShots)
                .setMaxRotationPerTick(config.maxRotationPerTick)
                .setTargetRange(config.targetRange)
                .setCooldown(config.cooldown)
                .setProjectile(projectile);
    }

    public static class TowerConfig extends ReflectedYaml {
        public List<Vector2> muzzleOffset;
        public Vector2 centerOffset;
        public float maxShots;
        public float maxRotationPerTick;
        public float targetRange;
        public long cooldown;
        public Color mapIndicatorColor;
        public String projectileName;
    }
}
