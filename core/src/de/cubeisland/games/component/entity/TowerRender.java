package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.util.BetterBatch;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
public class TowerRender extends Component<Entity> {
    private ShapeRenderer renderer;
    private Texture turretTexture;
    private Texture baseTexture;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta) {
        final Vector2 loc = getOwner().getLocation();
        final float scale = getOwner().getLevel().getMap().getScale();
        final BetterBatch batch = getOwner().getLevel().getScreen().getGame().getBatch();
        final Rotator rotator = getOwner().get(Rotator.class);
        final float rotation = getOwner().getVelocity().angle() - 90;

        final Vector2 basePos = rotator.getPos();
        final Vector2 turretPos = rotator.getAbsolutePos(loc, rotator.getCenterOffset().cpy().add(scale / 2, scale / 2), rotation);

        this.renderer.begin(Line);
        this.renderer.setColor(Color.RED);
        this.renderer.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
        this.renderer.end();

        batch.begin();
        batch.draw(baseTexture, basePos.x, basePos.y, scale, scale);
        batch.draw(turretTexture, turretPos.x, turretPos.y, 0, 0, scale, scale, 1, 1, rotation, 0, 0, turretTexture.getWidth(), turretTexture.getHeight(), false, false);
        batch.end();
    }

    public TowerRender setTurretTexture(Texture turretTexture) {
        this.turretTexture = turretTexture;
        return this;
    }
    public TowerRender setBaseTexture(Texture baseTexture) {
        this.baseTexture = baseTexture;
        return this;
    }
}
