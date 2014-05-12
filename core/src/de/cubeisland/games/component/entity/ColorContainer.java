package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

/**
 * Created by jonas on 11.05.14.
 */
public class ColorContainer extends Component<Entity> {
    private double amount = 0;
    private Color color = Color.BLUE;

    @Override
    public void update(float delta) {
    }

    public double getAmount() {
        return amount;
    }
    public Color getColor() {
        return color;
    }

    public ColorContainer setAmount(double amount) {
        this.amount = amount;
        return this;
    }
    public ColorContainer setColor(Color color) {
        this.color = color;
        return this;
    }

    public void shoot() {
        if (amount > 0) {
            this.amount--;
            if (amount == 0) {
                color = Color.LIGHT_GRAY;
            }
        }
    }
    public boolean hasShots() {
        if (amount > 0) {
            return true;
        }
    }
}
