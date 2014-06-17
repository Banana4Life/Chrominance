package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.component.Phase;
import de.cubeisland.games.entity.Entity;
import de.cubeisland.games.util.BetterBatch;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static de.cubeisland.games.component.TickPhase.RENDERING;

@Phase(RENDERING)
public class Render extends Component<Entity>
{
    private ShapeRenderer renderer;
    private Color color = Color.WHITE;
    private float radius = 10;
    private Texture texture = null;

    @Override
    protected void onInit() {
        super.onInit();
        renderer = getOwner().getLevel().getScreen().getGame().getShapeRenderer();
    }

    @Override
    public void update(float delta) {

        final Vector2 loc = getOwner().getLocation();

        if (getOwner().has(ColorContainer.class)) {
            this.color = getOwner().get(ColorContainer.class).getColor();
        }

        if (texture != null) {
            final BetterBatch batch = getOwner().getLevel().getScreen().getGame().getBatch();
            final float scale = radius * 2;
            final Vector2 rotatedLoc = Rotator.getAbsolutePos(loc, new Vector2(radius, radius), getOwner().getVelocity().angle());

            ColorContainer cc = getOwner().get(ColorContainer.class);
            Color ownColor = cc.getColor();
            batch.begin();
            getOwner().getLevel().getScreen().getGame().resources.shaders.saturation.setUniformf("overlay_color", ownColor);
            batch.draw(texture, rotatedLoc.x, rotatedLoc.y, 0, 0, scale, scale, 1, 1, getOwner().getVelocity().angle(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
            batch.end();
        } else {
            this.renderer.begin(Filled);
            this.renderer.setColor(this.color);
            this.renderer.circle(loc.x, loc.y, radius);
            this.renderer.end();
        }
        if (getOwner().has(Shield.class)) {
            final Shield shield = getOwner().get(Shield.class);
            this.renderer.begin(Line);
            this.renderer.setColor(shield.getColor());
            this.renderer.circle(loc.x, loc.y, radius + shield.getAdditionalRadius());
            this.renderer.end();
        }
    }

    public Color getColor() {
        return color;
    }

    public Render setColor(Color color) {
        this.color = color;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public Render setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public Render setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }
}
