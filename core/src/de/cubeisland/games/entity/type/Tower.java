package de.cubeisland.games.entity.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.entity.ColorContainer;
import de.cubeisland.games.component.entity.ProjectileLauncher;
import de.cubeisland.games.component.entity.SoundPlayer;
import de.cubeisland.games.component.entity.TowerRender;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.entity.EntityType;
import de.cubeisland.games.entity.EntityTypes;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Tower extends EntityType {
    private Vector2 centerOffset = new Vector2(0, 0);
    private List<Vector2> muzzleOffset = new ArrayList<>();
    private Texture turretTexture;
    private Texture baseTexture;
    private float maxShots = 10;
    private float maxRotationPerTick = 300;
    private float targetRange = 100;
    private long cooldown = 1500;

    public Tower() {
        add(TowerRender.class);
        add(ProjectileLauncher.class);
        add(ColorContainer.class);
        add(SoundPlayer.class);

        muzzleOffset.add(new Vector2(0, 0));
    }

    @Override
    protected void onInitialize(Entity e) {
        super.onInitialize(e);

        e.get(TowerRender.class)
                .setTurretTexture(turretTexture)
                .setBaseTexture(baseTexture);
        e.get(ProjectileLauncher.class)
                .setCooldown(cooldown, MILLISECONDS)
                .setTargetRange(targetRange)
                .setProjectile(EntityTypes.BULLET)
                .setCenterOffset(centerOffset)
                .setMuzzleOffset(muzzleOffset)
                .setMaxRotationPerTick(maxRotationPerTick);
        e.get(ColorContainer.class)
                .setColor(Color.BLUE)
                .setMaxAmount(maxShots)
                .refill();
        e.get(SoundPlayer.class)
                .setSound(e.getLevel().getScreen().getGame().soundManager.pew);
    }

    public Tower setCenterOffset(Vector2 centerOffset) {
        this.centerOffset = centerOffset;
        return this;
    }
    public Tower setMuzzleOffset(List<Vector2> muzzleOffset) {
        this.muzzleOffset = muzzleOffset;
        return this;
    }
    public Tower setTurretTexture(Texture texture) {
        this.turretTexture = texture;
        return this;
    }
    public Tower setBaseTexture(Texture texture) {
        this.baseTexture = texture;
        return this;
    }
    public Tower setMaxShots(float maxShots) {
        this.maxShots = maxShots;
        return this;
    }
    public Tower setMaxRotationPerTick(float maxRotationPerTick) {
        this.maxRotationPerTick = maxRotationPerTick;
        return this;
    }
    public Tower setTargetRange(float targetRange) {
        this.targetRange = targetRange;
        return this;
    }
    public Tower setCooldown(long cooldown) {
        this.cooldown = cooldown;
        return this;
    }
}
