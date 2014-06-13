package de.cubeisland.games.component;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.entity.Entity;

public class ColorRepoValue extends Component<Entity> {

    private ColorComponent component = ColorComponent.RED;

    @Override
    public void update(float delta) {

    }

    public ColorRepoValue setComponent(ColorComponent cc) {
        this.component = cc;
        return this;
    }

    public ColorComponent getComponent() {
        return component;
    }

    public static enum ColorComponent {
        RED(1, 0, 0),
        GREEN(0, 1, 0),
        BLUE(0, 0, 1);

        private final float redScale;
        private final float greenScale;
        private final float blueScale;

        ColorComponent(float redScale, float greenScale, float blueScale) {
            this.redScale = redScale;
            this.greenScale = greenScale;
            this.blueScale = blueScale;
        }

        public Color getColor(float damage) {
            return new Color((damage * redScale) / 255f, (damage * greenScale) / 255f, (damage * blueScale) / 255f, 1);
        }
    }
}
