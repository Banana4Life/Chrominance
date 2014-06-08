package de.cubeisland.games.resource.bag;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import de.cubeisland.engine.reflect.ReflectedYaml;
import de.cubeisland.engine.reflect.Reflector;
import de.cubeisland.games.entity.type.MuzzleFlash;
import de.cubeisland.games.entity.type.Projectile;
import de.cubeisland.games.resource.ResourceBag;

import java.lang.reflect.Field;

public class Projectiles extends ResourceBag<Projectile> {
    public Projectile slow;

    private final Reflector reflector;

    public Projectiles(Reflector reflector) {
        this.reflector = reflector;
    }

    @Override
    protected Projectile load(FileHandle basedir, Field field) {

        FileHandle projectileDir = fieldToFileHandle(field, basedir);
        final Texture projectileTexture = new Texture(projectileDir.child("projectile.png"));
        final Texture muzzleFlashTexture = new Texture(projectileDir.child("muzzleFlash.png"));

        ProjectileConfig config = this.reflector.load(ProjectileConfig.class, projectileDir.child("config.yml").read());

        return new Projectile()
                .setLaunchSpeed(config.launchSpeed)
                .setDamage(config.damage)
                .setCollisionVolume(config.collisionRadius)
                .setMuzzleFlash(new MuzzleFlash()
                                    .setTexture(muzzleFlashTexture)
                                    .setLifeTime(config.muzzleFlashLifeTime))
                .setTexture(projectileTexture);
    }

    public static class ProjectileConfig extends ReflectedYaml {
        public float launchSpeed;
        public int damage;
        public float collisionRadius;
        public long muzzleFlashLifeTime;
    }
}
