package de.cubeisland.games.component.entity;

import com.badlogic.gdx.graphics.Color;
import de.cubeisland.games.component.Component;
import de.cubeisland.games.entity.Entity;

public class ColorContainer extends Component<Entity> {
    private double amount = 0;
    private double maxAmount = 10;
    private Color color = Color.BLUE;

    private Color colorStep;

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
        if (getColor() != null) {
            this.colorStep = new Color().set((float) (getColor().r / amount), (float) (getColor().g / amount), (float) (getColor().b / amount), getColor().a);
        }
        return this;
    }
    public ColorContainer setColor(Color color) {
        this.color = color;
        return this;
    }

    public void shoot() {
        if (amount > 0) {
            this.amount--;
            //color.sub(this.colorStep);
            color = getColor().cpy().sub(this.colorStep);
            //color = new Color(getColor()).sub(this.colorStep); // This would work, but the towers stop shooting...
            if (amount == 0) {
                //color = new Color(getColor()).sub(this.colorStep);
            }
        }
    }
    public boolean hasShots() {
        if (amount > 0) {
            return true;
        }
        return false;
    }

    public ColorContainer setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }
    public void refill() {
        this.setAmount(maxAmount);
    }
}
