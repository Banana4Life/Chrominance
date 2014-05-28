package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.Chrominance;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.util.BetterBatch;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class TowerRender extends Component<Entity> {
    private final ShapeRenderer sr = new ShapeRenderer();

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation();
        final float scale = getOwner().getLevel().getMap().getScale();
        final Chrominance game = getOwner().getLevel().getGame();
        final BetterBatch batch = game.getBatch();
        final Texture baseTexture = game.textureManager.towerBase;
        final Texture turretTexture = game.textureManager.towerTurret;
        final ProjectileLauncher projectileLauncher = getOwner().get(ProjectileLauncher.class);
        final float rotation = projectileLauncher.getRotation() - 90;
        final float sinRot = (float)Math.sin(Math.toRadians(rotation));
        final float cosRot = (float)Math.cos(Math.toRadians(rotation));
        final Vector2 centerOffset = projectileLauncher.getCenterOffset();
        final Vector2 basePos = new Vector2(loc.x - (scale / 2), loc.y - (scale / 2));
        final float turretX = loc.x + centerOffset.x * cosRot + centerOffset.y * sinRot - (scale / 2 * (cosRot - sinRot));
        final float turretY = loc.y - centerOffset.x * sinRot - centerOffset.y * cosRot - (scale / 2 * (cosRot + sinRot));

        this.sr.begin(Line);
        this.sr.setColor(Color.RED);
        this.sr.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
        this.sr.end();

        batch.begin();
        batch.draw(baseTexture, basePos.x, basePos.y, scale, scale);
        batch.draw(turretTexture, turretX, turretY, 0, 0, scale, scale, 1, 1, rotation, 0, 0, turretTexture.getWidth(), turretTexture.getHeight(), false, false);
        batch.end();
    }
}
