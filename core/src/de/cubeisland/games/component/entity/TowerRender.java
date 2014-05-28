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
        final float rotation = getOwner().get(ProjectileLauncher.class).getRotation() - 90;
        final float x = loc.x - (scale / 2);
        final float y = loc.y - (scale / 2);
        final float turretX = loc.x - (scale / 2 * ((float)Math.cos(Math.toRadians(rotation)) - (float)Math.sin(Math.toRadians(rotation))));
        final float turretY = loc.y - (scale / 2 * ((float)Math.cos(Math.toRadians(rotation)) + (float)Math.sin(Math.toRadians(rotation))));

        this.sr.begin(Line);
        this.sr.setColor(Color.RED);
        this.sr.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
        this.sr.end();

        batch.begin();
        batch.draw(baseTexture, x, y, scale, scale);
        batch.draw(turretTexture, turretX, turretY, 0, 0, scale, scale, 1, 1, rotation, 0, 0, turretTexture.getWidth(), turretTexture.getHeight(), false, false);
        batch.end();
    }
}
