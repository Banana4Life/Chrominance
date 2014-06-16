package de.cubeisland.games.component.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.component.Require;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.util.BetterBatch;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Require(Focus.class)
@Phase(RENDERING)
public class TowerRender extends Component<Entity> {
    private Focus focus;
    private ShapeRenderer renderer;
    private Texture turretTexture;
    private Texture baseTexture;
    private Color rangeColor = Color.RED.cpy().sub(0, 0, 0, .9f);

    @Override
    protected void onInit() {
        super.onInit();
        focus = getOwner().get(Focus.class);
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

        if (this.focus.isFocused()) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            this.renderer.begin(Filled);
            this.renderer.setColor(this.rangeColor);
            this.renderer.circle(loc.x, loc.y, getOwner().get(ProjectileLauncher.class).getTargetRange());
            this.renderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

        ColorContainer cc = getOwner().get(ColorContainer.class);
        Color ownColor = cc.getColor();
        ownColor.a = (float) (1f / cc.getMaxAmount() * cc.getAmount());
        batch.begin();
        getOwner().getLevel().getScreen().getGame().resources.shaders.saturation.setUniformf("overlay_color", ownColor);
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

    public Color getRangeColor() {
        return rangeColor;
    }

    public TowerRender setRangeColor(Color rangeColor) {
        this.rangeColor = rangeColor;
        return this;
    }
}
